package data.datasource.deal

import data.api.model.deal.DealResponse

interface DealDataSource {
    suspend fun getDeals(): List<DealResponse>
}
