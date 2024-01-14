package data.datasource.alert

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.androbrain.gamehunter.AlertEntity
import com.androbrain.gamehunter.GameHunterDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class SqlDelightLocalAlertDataSource(
    db: GameHunterDatabase,
) : LocalAlertDataSource {
    private val emailQueries = db.emailEntityQueries
    private val alertQueries = db.alertEntityQueries
    override suspend fun insertEmail(email: String) {
        emailQueries.insert(email)
    }

    override suspend fun getEmail(): String? = emailQueries.get().executeAsOneOrNull()?.email
    override suspend fun insertAlert(
        email: String,
        gameTitle: String,
        gameID: String,
        price: String,
    ) {
        alertQueries.insert(
            id = null,
            email = email,
            gameTitle = gameTitle,
            gameId = gameID,
            price = price,
        )
    }

    override suspend fun deleteAlert(id: Long) {
        alertQueries.delete(id)
    }

    override suspend fun getAlerts(): Flow<List<AlertEntity>> =
        alertQueries.get().asFlow().mapToList(Dispatchers.IO)
}
