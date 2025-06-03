package visitor

import model.*

class ValidJsonObjectVisitor : JsonVisitor<Boolean> {

    override fun visitObject(obj: JsonObject): Boolean {
        val keys = mutableSetOf<String>()

        for ((key, value) in obj.properties) {
            if (key.isBlank()) return false
            if (!keys.add(key)) return false
            if (!value.accept(this)) return false
        }
        return true
    }

    override fun visitArray(array: JsonArray): Boolean {
        return array.elements.all { it.accept(this) }
    }

    override fun visitString(str: JsonString) = true
    override fun visitNumber(num: JsonNumber) = true
    override fun visitBoolean(bool: JsonBoolean) = true
    override fun visitNull(nullvalue: JsonNull) = true



}