package data.repository.deal

import data.api.model.deal.toModels
import data.datasource.deal.DealDataSource
import domain.deal.DealModel
import domain.deal.DealRepository

class DefaultDealRepository(
    private val dealDataSource: DealDataSource,
) : DealRepository {
    override suspend fun getDeals(): List<DealModel> = dealDataSource.getDeals().toModels()
}
