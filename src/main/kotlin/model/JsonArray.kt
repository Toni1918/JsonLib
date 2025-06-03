package model

import visitor.JsonVisitor

data class JsonArray(val elements: List<JsonValue>) : JsonValue {
    override fun toJsonString(): String {
        return elements.joinToString(prefix = "[", postfix = "]", separator = ",") { it.toJsonString() }
    }

    fun map(transform: (JsonValue) -> JsonValue): JsonArray {
        return JsonArray(elements.map(transform))
    }

    fun filter(predicate: (JsonValue) -> Boolean): JsonArray {
        return JsonArray(elements.filter(predicate))
    }

    override fun <T> accept(visitor: JsonVisitor<T>): T {
        return visitor.visitArray(this)
    }


}