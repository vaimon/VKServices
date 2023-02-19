package ru.vaimon.vkservices.models

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

data class VKService(
    val name: String,
    val description: String,
    @SerializedName(value = "icon_url") val iconUrl: String,
    @SerializedName(value = "service_url") val serviceUrl: String
) {
    companion object {
        public class Deserializer : JsonDeserializer<Array<VKService>> {
            override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): Array<VKService> {
                return Gson().fromJson(
                    json?.asJsonObject?.get("items"),
                    Array<VKService>::class.java
                )
            }

        }
    }
}