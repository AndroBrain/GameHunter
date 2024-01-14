package data.repository.alert

import data.datasource.alert.LocalAlertDataSource
import data.datasource.alert.RemoteAlertDataSource
import data.local.model.alert.toModels
import domain.alert.Alert
import domain.alert.AlertRepository
import domain.alert.DeleteAlertParams
import domain.alert.SetAlertParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultAlertRepository(
    private val remoteDataSource: RemoteAlertDataSource,
    private val localDataSource: LocalAlertDataSource,
) : AlertRepository {
    override suspend fun setAlert(params: SetAlertParams) {
        localDataSource.insertEmail(params.email)
        if (remoteDataSource.setAlert(params)) {
            localDataSource.insertAlert(
                email = params.email,
                gameTitle = params.gameTitle,
                gameID = params.gameID,
                price = params.price.toString(),
            )
        }
    }

    override suspend fun getEmail(): String? = localDataSource.getEmail()
    override suspend fun deleteAlert(params: DeleteAlertParams) {
        if (remoteDataSource.removeAlert(params)) {
            localDataSource.deleteAlert(params.gameID)
        }
    }

    override suspend fun getAlerts(): Flow<List<Alert>> =
        localDataSource.getAlerts().map { alerts -> alerts.toModels() }
}
