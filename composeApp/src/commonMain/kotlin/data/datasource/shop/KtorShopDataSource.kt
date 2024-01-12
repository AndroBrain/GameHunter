package data.datasource.shop

import data.api.HOST_URL
import data.api.model.shop.ShopResponse
import data.api.useClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class KtorShopDataSource : ShopDataSource {
    override suspend fun getShops(): List<ShopResponse> {
        val response = useClient { client ->
            client.get("$HOST_URL/stores")
        }
        return Json.decodeFromString<List<ShopResponse>>(
            response.bodyAsText()
        )
    }
}
