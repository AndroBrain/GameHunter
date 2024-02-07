package data.repository.alert

import data.core.ApiResponse
import data.datasource.alert.LocalAlertDataSource
import data.datasource.alert.RemoteAlertDataSource
import data.local.model.alert.toModels
import domain.alert.Alert
import domain.alert.AlertRepository
import domain.alert.DeleteAlertParams
import domain.alert.SetAlertParams
import domain.core.ResultErrorType
import domain.core.UseCaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultAlertRepository(
    private val remoteDataSource: RemoteAlertDataSource,
    private val localDataSource: LocalAlertDataSource,
) : AlertRepository {
    override suspend fun getEmail(): String? = localDataSource.getEmail()

    override suspend fun setAlert(params: SetAlertParams): UseCaseResult<Unit> {
        localDataSource.insertEmail(params.email)

        return when (val response = remoteDataSource.setAlert(params)) {
            is ApiResponse.Error -> UseCaseResult.Error(response.toResultErrorType())
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> if (response.value) {
                localDataSource.insertAlert(
                    email = params.email,
                    gameTitle = params.gameTitle,
                    gameID = params.gameID,
                    price = params.price.toString(),
                )
                UseCaseResult.Ok(Unit)
            } else {
                UseCaseResult.Error(ResultErrorType.UNKNOWN)
            }
        }
    }

    override suspend fun deleteAlert(params: DeleteAlertParams): UseCaseResult<Unit> =
        when (val response = remoteDataSource.removeAlert(params)) {
            is ApiResponse.Error -> UseCaseResult.Error(response.toResultErrorType())
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> if (response.value) {
                localDataSource.deleteAlert(params.gameID)
                UseCaseResult.Ok(Unit)
            } else {
                UseCaseResult.Error(ResultErrorType.UNKNOWN)
            }
        }

    override suspend fun getAlerts(): Flow<List<Alert>> =
        localDataSource.getAlerts().map { alerts -> alerts.toModels() }
}
