package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class JsonObjectTest {

    @Test
    fun testToJsonStringSimples(){
        val obj = JsonObject(
            mapOf(
                "nome" to JsonString("Ana"),
                "idade" to JsonNumber(20),
                "ativo" to JsonBoolean(true)
            )
        )

        assertEquals("{\"nome\":\"Ana\",\"idade\":20.0,\"ativo\":true}", obj.toJsonString())

    }

    @Test
    fun testFiltroApenasNumeros(){
        val obj = JsonObject(
            mapOf(
                "nome" to JsonString("Carlos"),
                "idade" to JsonNumber(25),
                "ativo" to JsonBoolean(true)
            )
        )

        val resultado = obj.filter { (_, valor) -> valor is JsonNumber }

        assertEquals("{\"idade\":25.0}", resultado.toJsonString())

    }

    @Test
    fun testToJsonStringComAspasNaChave(){
        val obj = JsonObject(
            mapOf(
                "nome\"completo" to JsonString("António Branco")
            )
        )

        assertEquals("{\"nome\\\"completo\":\"António Branco\"}", obj.toJsonString())

    }

    @Test
    fun testComparacaoJsonObject(){
        val esperado = JsonObject(mapOf(
            "nome" to JsonString("Ana"),
            "idade" to JsonNumber(20)
        ))

        val resultado = JsonObject(mapOf(
            "nome" to JsonString("Ana"),
            "idade" to JsonNumber(20)
        ))

        assertEquals(esperado, resultado)

    }

}