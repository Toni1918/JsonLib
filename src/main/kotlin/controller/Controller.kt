package controller

import annotations.Mapping
import annotations.Param
import annotations.Path
import model.*

@Mapping("/api")
class Controller {

    @Mapping("ints")
    fun demo(): List<Int> {
        return listOf(1, 2, 3)
    }

    @Mapping("pair")
    fun obj(): Pair<String, String> {
        return Pair("um", "dois")
    }

    @Mapping("path/{pathvar}")
    fun path(
        @Path("pathvar") pathvar: String): String {
        return "$pathvar!"
    }

    @Mapping("args")
    fun args(
        @Param("n") n: Int,
        @Param("text") text: String
    ): Map<String, String> {
        return mapOf(text to text.repeat(n))
    }
}
