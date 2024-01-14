package data.datasource.alert

import domain.alert.DeleteAlertParams
import domain.alert.SetAlertParams

interface RemoteAlertDataSource {
    suspend fun setAlert(params: SetAlertParams): Boolean
    suspend fun removeAlert(params: DeleteAlertParams): Boolean
}
