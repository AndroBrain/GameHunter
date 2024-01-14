package data.repository.alert

import data.datasource.alert.RemoteAlertDataSource
import domain.alert.AlertRepository
import domain.alert.SetAlertParams

class DefaultAlertRepository(
    private val remoteDataSource: RemoteAlertDataSource,
) : AlertRepository {
    override suspend fun setAlert(params: SetAlertParams) {
        remoteDataSource.setAlert(params)
    }
}
