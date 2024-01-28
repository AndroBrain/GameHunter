package domain.game.recently

import domain.deal.DealModel
import kotlinx.coroutines.flow.Flow

interface RecentlyViewedRepository {
    suspend fun insertGame(deal: DealModel)

    suspend fun get(): Flow<List<RecentlyViewedModel>>
}
