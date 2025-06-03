package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals


class JsonBooleanTest {

    @Test
    fun testToJsonStringTrue(){
        val valor = JsonBoolean(true)
        assertEquals("true", valor.toJsonString())
    }

    @Test
    fun testToJsonStringFalse(){
        val valor = JsonBoolean(false)
        assertEquals("false", valor.toJsonString())
    }

    @Test
    fun testComparacaoJsonBoolean(){
        val esperado = JsonBoolean(true)
        val resultado = JsonBoolean(true)
        assertEquals(esperado, resultado)
    }

}