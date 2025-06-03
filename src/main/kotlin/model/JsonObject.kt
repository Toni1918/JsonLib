package model

import visitor.JsonVisitor

data class JsonObject(val properties: Map<String, JsonValue>) : JsonValue {

    override fun toJsonString(): String {
        return properties.entries.joinToString(prefix = "{", postfix = "}", separator = ","){ (key, value) ->
            "\"${key.replace("\"", "\\\"")}\":${value.toJsonString() }"}
    }

    fun filter(predicate: (Map.Entry<String, JsonValue>) -> Boolean): JsonObject {
        return JsonObject(properties.filter(predicate))
    }

    override fun <T> accept(visitor: JsonVisitor<T>): T {
        return visitor.visitObject(this)
    }


    }

