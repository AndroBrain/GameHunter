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
        remoteDataSource.setAlert(params)
    }

    override suspend fun getEmail(): String? = localDataSource.getEmail()
}
