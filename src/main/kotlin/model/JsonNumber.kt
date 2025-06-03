package model

import visitor.JsonVisitor

data class JsonNumber(val value: Double) : JsonValue {

    constructor(value: Int) : this(value.toDouble())

    override fun toJsonString(): String {
        return value.toString()
    }

    override fun toString(): String {
        return toJsonString()
    }

    override fun <T> accept(visitor: JsonVisitor<T>): T {
        return visitor.visitNumber(this)
    }

}

