package data.repository.shop

import data.api.model.shop.toModels
import data.core.ApiResponse
import data.datasource.shop.ShopDataSource
import domain.core.ResultErrorType
import domain.core.UseCaseResult
import domain.shop.ShopModel
import domain.shop.ShopRepository

class DefaultShopRepository(
    private val shopDataSource: ShopDataSource,
) : ShopRepository {
    override suspend fun getShops(): UseCaseResult<List<ShopModel>> =
        when (val response = shopDataSource.getShops()) {
            is ApiResponse.Error -> UseCaseResult.Error(response.toResultErrorType())
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> UseCaseResult.Ok(response.value.toModels())
        }
}
