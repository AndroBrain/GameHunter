package data.datasource.alert

import data.api.HOST_URL
import data.api.useClient
import domain.alert.SetAlertParams
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

private const val ALERT_PATH = "alerts"
private const val ACTION_PARAM = "action"
private const val EMAIL_PARAM = "email"
private const val GAME_ID_PARAM = "gameID"
private const val PRICE_PARAM = "price"

private const val ACTION_SET = "set"

class KtorRemoteAlertDataSource : RemoteAlertDataSource {
    override suspend fun setAlert(params: SetAlertParams): Boolean {
        val response = useClient { client ->
            client.get("$HOST_URL/$ALERT_PATH") {
                url {
                    parameters.append(ACTION_PARAM, ACTION_SET)
                    parameters.append(EMAIL_PARAM, params.email)
                    parameters.append(GAME_ID_PARAM, params.gameID)
                    parameters.append(PRICE_PARAM, params.price.toString())
                }
            }
        }
        return Json.decodeFromString<Boolean>(response.bodyAsText())
    }
}
