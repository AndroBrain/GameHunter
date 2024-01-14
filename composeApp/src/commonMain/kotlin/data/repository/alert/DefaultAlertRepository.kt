package data.repository.alert

import data.datasource.alert.LocalAlertDataSource
import data.datasource.alert.RemoteAlertDataSource
import domain.alert.AlertRepository
import domain.alert.SetAlertParams

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
}
