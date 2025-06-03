package model

fun toJson(value: Any?): JsonValue {
    return when (value) {
        null -> JsonNull
        is String -> JsonString(value)
        is Boolean -> JsonBoolean(value)
        is Int, is Long -> JsonNumber((value as Number).toDouble())
        is Float, is Double -> JsonNumber((value as Number).toDouble())
        is Enum<*> -> JsonString(value.name)
        is List<*> -> JsonArray(value.map { toJson(it) })
        is Map<*, *> -> {
            if (value.keys.all { it is String }) {
                val mapped = value.entries.associate { (k, v) -> k as String to toJson(v) }
                JsonObject(mapped)
            } else {
                throw IllegalArgumentException("Todas as chaves do Map devem ser strings")
            }
        }
        is Pair<*, *> -> {
            val map = mapOf("first" to toJson(value.first), "second" to toJson(value.second))
            JsonObject(map)
        }
        else -> {
            val map = mutableMapOf<String, JsonValue>()
            val fields = value.javaClass.declaredFields
            for (field in fields) {
                field.isAccessible = true
                val nome = field.name
                val valor = field.get(value)
                map[nome] = toJson(valor)
            }
            JsonObject(map)
        }
    }
}
