package visitor

import model.*

class HomogeneousArrayVisitor : JsonVisitor<Boolean> {

    override fun visitArray(array: JsonArray): Boolean {
        val elementos = array.elements.filterNot { it is JsonNull }

        if (elementos.isEmpty()) return true

        val primeiroTipo = elementos.first()::class

        return elementos.all {
            it::class == primeiroTipo && it.accept(this)
        }

    }

    override fun visitObject(obj: JsonObject): Boolean {
        return obj.properties.values.all { it.accept(this) }
    }

    override fun visitBoolean(bool: JsonBoolean) = true
    override fun visitNull(nullvalue: JsonNull) = true
    override fun visitNumber(num: JsonNumber) = true
    override fun visitString(str: JsonString) = true


}