package data.datasource.deal

import data.api.model.deal.DealResponse
import domain.deal.DealParams

interface DealDataSource {
    suspend fun getDeals(params: DealParams): List<DealResponse>
}
