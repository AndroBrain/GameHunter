package data.datasource.alert

import com.androbrain.gamehunter.GameHunterDatabase

class SqlDelightLocalAlertDataSource(
    db: GameHunterDatabase,
) : LocalAlertDataSource {
    private val emailQueries = db.emailEntityQueries
    override suspend fun insertEmail(email: String) {
        emailQueries.insert(email)
    }

    override suspend fun getEmail(): String? = emailQueries.get().executeAsOneOrNull()?.email
}
