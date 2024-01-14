package data.datasource.alert

import domain.alert.SetAlertParams

interface RemoteAlertDataSource {
    suspend fun setAlert(params: SetAlertParams)
}
