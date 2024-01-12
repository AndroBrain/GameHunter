package domain.shop

interface ShopRepository {
    suspend fun getShops(): List<ShopModel>
}
