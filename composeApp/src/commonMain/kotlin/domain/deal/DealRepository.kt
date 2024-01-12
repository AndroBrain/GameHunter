package domain.deal

import domain.deal.game.GameWithDealsModel

interface DealRepository {
    suspend fun getDeals(params: DealParams): List<DealModel>
    suspend fun getGameWithDeals(gameID: String): GameWithDealsModel
}
