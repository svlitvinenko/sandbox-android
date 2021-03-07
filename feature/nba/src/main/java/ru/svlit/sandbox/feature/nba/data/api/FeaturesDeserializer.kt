package ru.svlit.sandbox.feature.nba.data.api

import com.google.gson.*
import ru.svlit.sandbox.feature.nba.data.NbaCreator
import ru.svlit.sandbox.feature.nba.models.data.NbaFeatureData
import java.lang.reflect.Type

/**
 * Преобразователь типа точки входа в продукт из сериализованной формы.
 */
class FeaturesDeserializer(private val gson: Gson, private val creators: Set<NbaCreator<*>>) : JsonDeserializer<NbaFeatureData> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): NbaFeatureData? {
        val jsonObject: JsonObject = json.asJsonObject
        val type: String = jsonObject["type"].asString

        val clazz: Class<out NbaFeatureData> = creators.firstOrNull { it.featureKey == type }?.nbaDataClass ?: return null
        return gson.fromJson(jsonObject[type], clazz)
    }

}