package model

import visitor.JsonVisitor

data class JsonBoolean(val value: Boolean) : JsonValue {
    override fun toJsonString(): String {
        return value.toString()
    }

    override fun <T> accept(visitor: JsonVisitor<T>): T {
        return visitor.visitBoolean(this)
    }

}