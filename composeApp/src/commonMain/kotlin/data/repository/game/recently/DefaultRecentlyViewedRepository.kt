package data.repository.game.recently

import data.datasource.game.recently.RecentlyViewedDataSource
import data.datasource.game.recently.model.toModels
import data.datasource.game.recently.model.toRecentlyViewedEntity
import domain.deal.DealModel
import domain.game.recently.RecentlyViewedModel
import domain.game.recently.RecentlyViewedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultRecentlyViewedRepository(
    private val recentlyViewedDataSource: RecentlyViewedDataSource,
) : RecentlyViewedRepository {
    override suspend fun insertGame(deal: DealModel) {
        recentlyViewedDataSource.insert(deal.toRecentlyViewedEntity())
    }

    override suspend fun get(): Flow<List<RecentlyViewedModel>> =
        recentlyViewedDataSource.get()
            .map { entities -> entities.toModels() }
}
