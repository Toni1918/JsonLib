package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class JsonNumberTest {

    @Test
    fun testToJsonStringComInteiroVirgula(){
        val valor = JsonNumber(42.0)
        assertEquals("42.0", valor.toJsonString())
    }

    @Test
    fun testToJsonStringComInteiro(){
        val valor = JsonNumber(42)
        assertEquals("42.0", valor.toJsonString())
    }

    @Test
    fun testToJsonStringComDecimal(){
        val valor = JsonNumber(3.1416)
        assertEquals("3.1416", valor.toJsonString())
    }

    @Test
    fun testToJsonStringComZero(){
        val valor = JsonNumber(0.0)
        assertEquals("0.0", valor.toJsonString())
    }

    @Test
    fun testToJsonStringComNegativo(){
        val valor = JsonNumber(-1.0)
        assertEquals("-1.0", valor.toJsonString())
    }

    @Test
    fun testComparacaoJsonNumber(){
        val esperado = JsonNumber(42.0)
        val resultado = JsonNumber(42)
        assertEquals(esperado, resultado)
    }

}