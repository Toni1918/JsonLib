package server

import annotations.Mapping
import annotations.Param
import annotations.Path
import model.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.reflect.Method
import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread

class GetJson {

    fun start(port: Int, controller: Any) {
        start(port, listOf(controller))
    }
}

fun start(port: Int, controllers: List<Any>) {
    val routes = mutableMapOf<String, (Map<String, String>) -> JsonValue>()

    for (controller in controllers) {
        val basePath = controller::class.java.getAnnotation(Mapping::class.java)?.value?.trim('/') ?: ""

        for (method in controller::class.java.methods) {
            val mapping = method.getAnnotation(Mapping::class.java)
            if (mapping != null) {
                val subPath = mapping.value.trim('/')
                val fullPath = "/${listOf(basePath, subPath).filter { it.isNotBlank() }.joinToString("/")}"

                routes[fullPath] = { params ->
                    val args = method.parameters.map { param ->
                        when {
                            param.isAnnotationPresent(Path::class.java) -> {
                                val name = param.getAnnotation(Path::class.java).value
                                convert(params[name], param.type)
                            }
                            param.isAnnotationPresent(Param::class.java) -> {
                                val name = param.getAnnotation(Param::class.java).value
                                convert(params[name], param.type)
                            }
                            else -> null
                        }
                    }.toTypedArray()

                    val result = method.invoke(controller, *args)
                    if (result is JsonValue) result else toJson(result)
                }

                println("Rota registada: $fullPath → ${method.name}")
            }
        }
    }

    val serverSocket = ServerSocket(port)
    println("Servidor iniciado na porta $port")

    while (true) {
        val socket = serverSocket.accept()
        thread {
            handle(socket, routes)
        }
    }
}

private fun convert(value: String?, type: Class<*>): Any? {
    return when (type) {
        String::class.java -> value
        Int::class.java, Integer::class.java -> value?.toInt()
        Double::class.java -> value?.toDouble()
        Boolean::class.java -> value?.toBoolean()
        else -> null
    }
}

private fun handle(socket: Socket, routes: Map<String, (Map<String, String>) -> JsonValue>) {
    socket.use {
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val writer = PrintWriter(socket.getOutputStream())

        val requestLine = reader.readLine()
        if (requestLine == null || requestLine.isBlank()) return

        val uri = requestLine.split(" ")[1]
        val (path, query) = uri.split("?", limit = 2).let {
            it[0] to if (it.size > 1) it[1] else ""
        }

        val queryParams = query.split("&").filter { it.contains("=") }.associate {
            val (k, v) = it.split("=")
            k to v
        }

        println("Rota recebida: $path")
        println("Query params: $queryParams")


        val directMatch = routes[path]

        val response = if (directMatch != null) {
            directMatch(queryParams)
        } else {
            // Match com path variables
            val matchedEntry = routes.entries.firstOrNull { (route, _) ->
                val routeRegex = route.replace(Regex("""\{[^/]+}"""), "[^/]+").toRegex()
                routeRegex.matches(path)
            }

            matchedEntry?.let { (route, handler) ->
                val routeParts = route.split("/")
                val pathParts = path.split("/")

                val pathVariables = routeParts.zip(pathParts).filter { (r, _) ->
                    r.startsWith("{") && r.endsWith("}")
                }.associate { (r, p) ->
                    val name = r.removePrefix("{").removeSuffix("}")
                    name to p
                }

                val allParams = queryParams + pathVariables
                handler(allParams)
            }
        }

        if (response != null) {
            writer.println("HTTP/1.1 200 OK")
            writer.println("Content-Type: application/json")
            writer.println()
            writer.println(response.toJsonString())
        } else {
            writer.println("HTTP/1.1 404 Not Found")
            writer.println("Content-Type: text/plain")
            writer.println()
            writer.println("Rota não encontrada.")
        }

        writer.flush()
    }
}



