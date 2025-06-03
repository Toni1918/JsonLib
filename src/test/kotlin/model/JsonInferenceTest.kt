package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals




data class Course(
    val name: String,
    val ects: Int,
    val eval: List<EvalItem>
)


class JsonInferenceTest {

    @Test
    fun testEvalItemToJson(){
        val item = EvalItem(0.4, EvalType.TEST)
        val esperado = JsonObject(
            mapOf(
                "weight" to JsonNumber(0.4),
                "type" to JsonString("TEST")
            )
        )
        assertEquals(esperado, toJson(item))
    }


    @Test
    fun testCourseToJson(){
        val course = Course(
            "PA",
            6,
            listOf(
                EvalItem(0.4, EvalType.TEST),
                EvalItem(0.6, EvalType.PROJECT),
            )
        )

        val esperado = JsonObject(
            mapOf(
                "name" to JsonString("PA"),
                "ects" to JsonNumber(6.0),
                "eval" to JsonArray(
                    listOf(
                        JsonObject(
                            mapOf(
                                "weight" to JsonNumber(0.4),
                                "type" to JsonString("TEST")
                            )
                        ),
                        JsonObject(
                            mapOf(
                                "weight" to JsonNumber(0.6),
                                "type" to JsonString("PROJECT")
                            )
                        )
                    )
                )
            )
        )
        assertEquals(esperado, toJson(course))
    }


    @Test
    fun testMapToJson(){
        val mapa = mapOf("x" to 1, "y" to 2)
        val esperado = JsonObject(
            mapOf(
                "x" to JsonNumber(1.0),
                "y" to JsonNumber(2.0)
            )
        )
        assertEquals(esperado, toJson(mapa))
    }

}