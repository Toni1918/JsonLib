package visitor

import model.*

interface JsonVisitor<T> {
    fun visitObject(obj: JsonObject): T
    fun visitArray(array: JsonArray): T
    fun visitString(str: JsonString): T
    fun visitNumber(num: JsonNumber): T
    fun visitBoolean(bool: JsonBoolean): T
    fun visitNull(nullvalue: JsonNull): T
}