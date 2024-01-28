package data.datasource.game.recently

import com.androbrain.gamehunter.RecentlyViewedGameEntity
import kotlinx.coroutines.flow.Flow

interface RecentlyViewedDataSource {
    suspend fun insert(entity: RecentlyViewedGameEntity)

    fun get(): Flow<List<RecentlyViewedGameEntity>>
}
