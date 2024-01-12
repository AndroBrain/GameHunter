package data.datasource.shop

import data.api.model.shop.ShopResponse

interface ShopDataSource {
    suspend fun getShops(): List<ShopResponse>
}
