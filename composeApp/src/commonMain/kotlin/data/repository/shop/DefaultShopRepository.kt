package data.repository.shop

import data.api.model.shop.toModels
import data.datasource.shop.ShopDataSource
import domain.shop.ShopModel
import domain.shop.ShopRepository

class DefaultShopRepository(
    private val shopDataSource: ShopDataSource,
) : ShopRepository {
    override suspend fun getShops(): List<ShopModel> =
        shopDataSource.getShops().toModels()
}
