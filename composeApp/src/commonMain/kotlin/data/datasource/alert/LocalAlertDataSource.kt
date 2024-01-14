package data.datasource.alert

import com.androbrain.gamehunter.AlertEntity
import kotlinx.coroutines.flow.Flow

interface LocalAlertDataSource {
    suspend fun insertEmail(email: String)
    suspend fun getEmail(): String?
    suspend fun insertAlert(email: String, gameTitle: String, gameID: String, price: String)
    suspend fun deleteAlert(id: String)
    suspend fun getAlerts(): Flow<List<AlertEntity>>
}
