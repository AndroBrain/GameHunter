package domain.deal

import domain.core.UseCaseResult
import domain.deal.game.GameWithDealsModel

interface DealRepository {
    suspend fun getDeals(params: DealParams): UseCaseResult<List<DealModel>>
    suspend fun getGameWithDeals(gameID: String): UseCaseResult<GameWithDealsModel>
}
