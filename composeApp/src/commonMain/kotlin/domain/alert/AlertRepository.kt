package domain.alert

import kotlinx.coroutines.flow.Flow

interface AlertRepository {
    suspend fun setAlert(params: SetAlertParams)

    suspend fun getEmail(): String?
    suspend fun deleteAlert(params: DeleteAlertParams)
    suspend fun getAlerts(): Flow<List<Alert>>
}
