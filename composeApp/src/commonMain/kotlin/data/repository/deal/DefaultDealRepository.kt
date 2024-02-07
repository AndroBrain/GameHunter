package data.repository.deal

import data.api.model.deal.game.toModel
import data.api.model.deal.toModels
import data.core.ApiResponse
import data.datasource.deal.DealDataSource
import domain.core.ResultErrorType
import domain.core.UseCaseResult
import domain.deal.DealParams
import domain.deal.DealRepository

class DefaultDealRepository(
    private val dealDataSource: DealDataSource,
) : DealRepository {
    override suspend fun getDeals(params: DealParams) =
        when (val response = dealDataSource.getDeals(params)) {
            is ApiResponse.Error -> UseCaseResult.Error(response.toResultErrorType())
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> UseCaseResult.Ok(response.value.toModels())
        }

    override suspend fun getGameWithDeals(gameID: String) =
        when (val response = dealDataSource.getGameWithDeals(gameID)) {
            is ApiResponse.Error -> UseCaseResult.Error(response.toResultErrorType())
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> UseCaseResult.Ok(response.value.toModel())
        }
}
