package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class JsonNullTest {

    @Test
    fun testToJsonString(){
        val valor = JsonNull
        assertEquals("null", valor.toJsonString())
    }

    @Test
    fun testComparacaoJsonNull(){
        val valor1 = JsonNull
        val valor2 = JsonNull
        assertEquals(valor1, valor2)
    }

}