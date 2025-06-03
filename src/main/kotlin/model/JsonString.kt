package model

import visitor.JsonVisitor

data class JsonString(val value: String) : JsonValue {
    override fun toJsonString(): String {
        return "\"${value.replace("\"", "\\\"")}\""
    }

    override fun <T> accept(visitor: JsonVisitor<T>): T {
        return visitor.visitString(this)
    }



}