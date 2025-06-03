package visitor

import model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import visitor.HomogeneousArrayVisitor

class HomogeneousArrayVisitorTest {

    @Test
    fun testArrayHomogeneoNumeros() {
        val array = JsonArray(listOf(
            JsonNumber(1),
            JsonNumber(2),
            JsonNumber(3)
        ))
        assertTrue(array.accept(HomogeneousArrayVisitor()))
    }

    @Test
    fun testArrayHomogeneoComNulls() {
        val array = JsonArray(listOf(
            JsonNumber(1),
            JsonNull,
            JsonNumber(2),
            JsonNull
        ))
        assertTrue(array.accept(HomogeneousArrayVisitor()))
    }

    @Test
    fun testArrayMisto() {
        val array = JsonArray(listOf(
            JsonNumber(1),
            JsonString("texto")
        ))
        assertFalse(array.accept(HomogeneousArrayVisitor()))
    }

    @Test
    fun testArraySoComNulls() {
        val array = JsonArray(listOf(
            JsonNull,
            JsonNull
        ))
        assertTrue(array.accept(HomogeneousArrayVisitor()))
    }

    @Test
    fun testArrayComObjetosDoMesmoTipo() {
        val obj1 = JsonObject(mapOf("a" to JsonNumber(1)))
        val obj2 = JsonObject(mapOf("a" to JsonNumber(2)))
        val array = JsonArray(listOf(obj1, obj2))
        assertTrue(array.accept(HomogeneousArrayVisitor()))
    }

    @Test
    fun testArrayComArraysAninhadosHomogeneos() {
        val a1 = JsonArray(listOf(JsonNumber(1), JsonNumber(2)))
        val a2 = JsonArray(listOf(JsonNumber(3), JsonNumber(4)))
        val array = JsonArray(listOf(a1, a2))
        assertTrue(array.accept(HomogeneousArrayVisitor()))
    }

    @Test
    fun testArrayComArraysAninhadosMistos() {
        val a1 = JsonArray(listOf(JsonNumber(1), JsonString("texto")))
        val a2 = JsonArray(listOf(JsonNumber(2)))
        val array = JsonArray(listOf(a1, a2))
        assertFalse(array.accept(HomogeneousArrayVisitor()))
    }
}
