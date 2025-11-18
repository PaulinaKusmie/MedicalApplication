package com.example.composeactivity.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type


class BooleanTypeAdapter : JsonDeserializer<Boolean?> {
    @Throws(JsonParseException::class)
    public override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Boolean {
        if (json.isJsonPrimitive()) {
            val prim = json.getAsJsonPrimitive()
            if (prim.isBoolean()) {
                return prim.getAsBoolean()
            } else if (prim.isNumber()) {
                val intValue = prim.getAsInt()
                return intValue != 0
            } else if (prim.isString()) {
                // optionally handle string "true"/"false" as well
                return prim.getAsString().toBoolean()
            }
        }
        return false // default fallback
    }
}