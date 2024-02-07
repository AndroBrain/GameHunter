package data.datasource.shop

import data.api.model.shop.ShopResponse
import data.core.ApiResponse

interface ShopDataSource {
    suspend fun getShops(): ApiResponse<List<ShopResponse>>
}
