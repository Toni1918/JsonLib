package model

import visitor.JsonVisitor

interface JsonValue{
    abstract fun toJsonString(): String
    abstract fun <T> accept(visitor: JsonVisitor<T>): T
}

