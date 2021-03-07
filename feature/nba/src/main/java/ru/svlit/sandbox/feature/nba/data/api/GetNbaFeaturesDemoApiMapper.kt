package ru.svlit.sandbox.feature.nba.data.api

import com.google.gson.Gson
import kotlinx.coroutines.delay
import ru.svlit.sandbox.feature.nba.data.GetNbaFeaturesApiMapper
import ru.svlit.sandbox.feature.nba.models.data.NbaFeaturesResponse

class GetNbaFeaturesDemoApiMapper(
    private val gson: Gson
) : GetNbaFeaturesApiMapper {

    private val responseString = """
            {
                "features": [
                    {
                        "type": "fines",
                        "fines": {
                            "found": 4,
                            "sum": 500
                        }
                    },
                    {
                        "type": "weather",
                        "weather": {
                            "value": -3,
                            "location": "Podolsk"
                        }
                    }
                ]
            }
        """.trimIndent()

    override suspend fun get(): NbaFeaturesResponse {
        delay(2000)
        val response: NbaFeaturesResponse = gson.fromJson(responseString, NbaFeaturesResponse::class.java)

        @Suppress("UselessCallOnCollection")
        return response.copy(
            features = response.features.filterNotNull()
        )
    }
}