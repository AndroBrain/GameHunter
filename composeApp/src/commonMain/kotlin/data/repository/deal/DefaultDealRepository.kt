package data.repository.deal

import data.api.model.deal.game.toModel
import data.api.model.deal.toModels
import data.datasource.deal.DealDataSource
import domain.deal.DealModel
import domain.deal.DealParams
import domain.deal.DealRepository
import domain.deal.game.GameWithDealsModel

class DefaultDealRepository(
    private val dealDataSource: DealDataSource,
) : DealRepository {
    override suspend fun getDeals(params: DealParams): List<DealModel>? =
        dealDataSource.getDeals(params)?.toModels()

    override suspend fun getGameWithDeals(gameID: String): GameWithDealsModel =
        dealDataSource.getGameWithDeals(gameID).toModel()
}
