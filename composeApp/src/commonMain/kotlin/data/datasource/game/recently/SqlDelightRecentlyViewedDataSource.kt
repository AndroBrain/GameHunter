package data.datasource.game.recently

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.androbrain.gamehunter.GameHunterDatabase
import com.androbrain.gamehunter.RecentlyViewedGameEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class SqlDelightRecentlyViewedDataSource(
    db: GameHunterDatabase,
) : RecentlyViewedDataSource {
    private val queries = db.recentlyViewedGameEntityQueries
    override suspend fun insert(entity: RecentlyViewedGameEntity) {
        queries.insert(
            thumb = entity.thumb,
            title = entity.title,
            steamRatingText = entity.steamRatingText,
            steamRatingCount = entity.steamRatingCount,
            metacriticRating = entity.metacriticRating,
            cheapSharkGameID = entity.cheapSharkGameID
        )
    }

    override fun get(): Flow<List<RecentlyViewedGameEntity>> =
        queries.get().asFlow().mapToList(Dispatchers.IO)
}
