package PhoneBookProjectKotlin

class JsonObject {
    private val map = mutableMapOf<String, Any?>()
    fun addProperty(key: String, value: Any?){
        map[key] = value
    }

    override fun toString(): String {
        return map.entries.joinToString(prefix = "{", postfix = "}" ) {(key,value) -> "\"$key\": ${value.toJsonString()}"}
    }
}

class JsonArray{
    private val list = mutableListOf<Any?>()

    fun addElement(value: Any?){
        list.add(value)
    }

    override fun toString(): String {
        return list.joinToString(prefix = "{", postfix = "}" ) {it.toJsonString()}
    }
}

fun jsonObject(init: JsonObject.() -> Unit): JsonObject{
    val jsonObject = JsonObject()
    jsonObject.init()
    return jsonObject
}

fun jsonArray(init: JsonArray.() -> Unit): JsonArray{
    val jsonArray = JsonArray()
    jsonArray.init()
    return jsonArray
}

private fun Any?.toJsonString(): String{
    return when(this){
        is String -> "\"$this\""
        is Number, is Boolean -> this.toString()
        is List<*> -> this.toString()
        is JsonObject -> this.toString()
        is JsonArray -> this.toString()
        null -> "null"
        else -> throw IllegalArgumentException("Unsupported type")
    }
}