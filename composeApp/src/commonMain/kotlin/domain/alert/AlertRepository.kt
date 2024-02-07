package domain.alert

import domain.core.UseCaseResult
import kotlinx.coroutines.flow.Flow

interface AlertRepository {
    suspend fun getEmail(): String?

    suspend fun setAlert(params: SetAlertParams): UseCaseResult<Unit>
    suspend fun deleteAlert(params: DeleteAlertParams): UseCaseResult<Unit>
    suspend fun getAlerts(): Flow<List<Alert>>
}
