package model

import visitor.JsonVisitor

object JsonNull : JsonValue {
    override fun toJsonString(): String {
        return "null"
    }

    override fun <T> accept(visitor: JsonVisitor<T>): T {
        return visitor.visitNull(this)
    }

}