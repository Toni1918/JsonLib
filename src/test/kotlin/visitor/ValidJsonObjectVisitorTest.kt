package visitor

import model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import visitor.ValidJsonObjectVisitor

class ValidJsonObjectVisitorTest {

    @Test
    fun testObjetoValido(){
        val obj = JsonObject(
            mapOf(
                "nome" to JsonString("Ana"),
                "idade" to JsonNumber(30),
                "ativo" to JsonBoolean(true)
            )
        )

        val resultado = obj.accept(ValidJsonObjectVisitor())
        assertTrue(resultado)

    }

    @Test
    fun testObjetoComChaveVazia(){
        val obj = JsonObject(
            mapOf(
                "" to JsonString("valor")
            )
        )

        val resultado = obj.accept(ValidJsonObjectVisitor())
        assertFalse(resultado)

    }

    @Test
    fun testObjetoComChaveEmBranco(){
        val obj = JsonObject(
            mapOf(
                "  " to JsonString("valor")
            )
        )

        val resultado = obj.accept(ValidJsonObjectVisitor())
        assertFalse(resultado)

    }

    @Test
    fun testObjetoAninhadoValido(){
        val inner = JsonObject(
            mapOf(
                "cidade" to JsonString("Lisboa")
            )
        )
        val obj = JsonObject(
            mapOf(
                "dados" to inner
            )
        )

        val resultado = obj.accept(ValidJsonObjectVisitor())
        assertTrue(resultado)

    }

    @Test
    fun testObjetoAninhadoInvalido(){
        val inner = JsonObject(
            mapOf(
                "  " to JsonString("valor")
            )
        )
        val obj = JsonObject(
            mapOf(
                "dados" to inner
            )
        )
        val resultado = obj.accept(ValidJsonObjectVisitor())
        assertFalse(resultado)
    }

    @Test
    fun testArrayDentroDoObjetoValido(){
        val array = JsonArray(listOf(
            JsonString("um"),
            JsonString("dois")
        ))
        val obj = JsonObject(
            mapOf(
                "itens" to array
            )
        )

        val resultado = obj.accept(ValidJsonObjectVisitor())
        assertTrue(resultado)

    }

    @Test
    fun testArrayDentroDoObjetoInvalido(){
        val innerObj = JsonObject(mapOf("  " to JsonNumber(5)))
        val array = JsonArray(listOf(innerObj))
        val obj = JsonObject(
            mapOf(
                "conteudo" to array
            )
        )

        val resultado = obj.accept(ValidJsonObjectVisitor())
        assertFalse(resultado)

    }

}