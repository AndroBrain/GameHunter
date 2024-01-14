package domain.alert

interface AlertRepository {
    suspend fun setAlert(params: SetAlertParams)
}
