package data.datasource.alert

import data.core.ApiResponse
import domain.alert.DeleteAlertParams
import domain.alert.SetAlertParams

interface RemoteAlertDataSource {
    suspend fun setAlert(params: SetAlertParams): ApiResponse<Boolean>
    suspend fun removeAlert(params: DeleteAlertParams): ApiResponse<Boolean>
}
