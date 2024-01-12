package data.datasource.deal

import data.api.model.deal.DealResponse
import data.api.model.deal.game.GameWithDealsReponse
import domain.deal.DealParams

interface DealDataSource {
    suspend fun getDeals(params: DealParams): List<DealResponse>
    suspend fun getGameWithDeals(gameID: String): GameWithDealsReponse
}
