package model

fun main(){

    val properties : Map<String, JsonValue> = mapOf(
        "nome" to JsonString("Ana"),
        "idade" to JsonNumber(30.0),
        "ativo" to JsonBoolean(true)
    )

    val valor = JsonString("Olá")

    properties.forEach { (chave, valor) -> println("$chave = ${valor.toJsonString()}") }

    println(properties)

}