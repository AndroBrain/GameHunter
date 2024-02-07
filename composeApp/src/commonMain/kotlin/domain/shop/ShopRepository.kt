package domain.shop

import domain.core.UseCaseResult

interface ShopRepository {
    suspend fun getShops(): UseCaseResult<List<ShopModel>>
}
