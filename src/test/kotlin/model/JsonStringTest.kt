package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class JsonStringTest {

    @Test
    fun testToJsonStringComTextoSimples() {
        val valor = JsonString("Olá")
        assertEquals("\"Olá\"", valor.toJsonString())
    }

    @Test
    fun testToJsonStringComAspas() {
        val valor = JsonString("Olá \"mundo\"")
        assertEquals("\"Olá \\\"mundo\\\"\"", valor.toJsonString())
    }

    @Test
    fun testToJsonStringComMultiplasAspasDuplas(){
        val valor = JsonString("\"um\" e \"dois\"")
        assertEquals("\"\\\"um\\\" e \\\"dois\\\"\"", valor.toJsonString())
    }

    @Test
    fun testComparacaoJsonString(){
        val esperado = JsonString("Olá")
        val resultado = JsonString("Olá")
        assertEquals(esperado, resultado)
    }

}