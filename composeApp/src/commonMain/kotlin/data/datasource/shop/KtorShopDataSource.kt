package data.datasource.shop

import data.api.HOST_URL
import data.api.model.shop.ShopResponse
import data.api.useClient
import data.core.ApiResponse
import io.ktor.client.request.get

class KtorShopDataSource : ShopDataSource {
    override suspend fun getShops(): ApiResponse<List<ShopResponse>> = useClient { client ->
        client.get("$HOST_URL/stores")
    }
}
