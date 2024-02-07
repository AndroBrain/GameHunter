package data.datasource.alert

import data.api.HOST_URL
import data.api.useClient
import data.core.ApiResponse
import domain.alert.DeleteAlertParams
import domain.alert.SetAlertParams
import io.ktor.client.request.get

private const val ALERT_PATH = "alerts"
private const val ACTION_PARAM = "action"
private const val EMAIL_PARAM = "email"
private const val GAME_ID_PARAM = "gameID"
private const val PRICE_PARAM = "price"

private const val ACTION_SET = "set"
private const val ACTION_DELETE = "delete"

class KtorRemoteAlertDataSource : RemoteAlertDataSource {
    override suspend fun setAlert(params: SetAlertParams): ApiResponse<Boolean> =
        useClient { client ->
            client.get("$HOST_URL/$ALERT_PATH") {
                url {
                    parameters.append(ACTION_PARAM, ACTION_SET)
                    parameters.append(EMAIL_PARAM, params.email)
                    parameters.append(GAME_ID_PARAM, params.gameID)
                    parameters.append(PRICE_PARAM, params.price.toString())
                }
            }
        }

    override suspend fun removeAlert(params: DeleteAlertParams): ApiResponse<Boolean> =
        useClient { client ->
            client.get("$HOST_URL/$ALERT_PATH") {
                url {
                    parameters.append(ACTION_PARAM, ACTION_DELETE)
                    parameters.append(EMAIL_PARAM, params.email)
                    parameters.append(GAME_ID_PARAM, params.gameID)
                }
            }
        }
}
