package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class JsonArrayTest {

    @Test
    fun testToJsonStringSimples(){
        val array = JsonArray(listOf(
            JsonNumber(1),
            JsonString("Olá"),
            JsonBoolean(true)
        ))

        assertEquals("[1.0,\"Olá\",true]", array.toJsonString())

    }

    @Test
    fun testMapDobrarNumeros(){
        val array = JsonArray(listOf(
            JsonNumber(1),
            JsonNumber(2),
            JsonNumber(3)
        ))

        val resultado = array.map {
            if (it is JsonNumber) JsonNumber(it.value * 2)
            else it
        }

        assertEquals("[2.0,4.0,6.0]", resultado.toJsonString())

    }

    @Test
    fun testFiltrarNumeros(){
        val array = JsonArray(listOf(
            JsonNumber(1),
            JsonString("texto"),
            JsonNumber(3)
        ))

        val resultado = array.filter { it is JsonNumber }

        assertEquals("[1.0,3.0]", resultado.toJsonString())

    }

    @Test
    fun testComapracaoJsonArray(){
        val esperado = JsonArray(listOf(
            JsonNumber(1),
            JsonString("texto"),
            JsonBoolean(true)
        ))

        val resultado = JsonArray(listOf(
            JsonNumber(1),
            JsonString("texto"),
            JsonBoolean(true)
        ))

        assertEquals(esperado, resultado)

    }

}

