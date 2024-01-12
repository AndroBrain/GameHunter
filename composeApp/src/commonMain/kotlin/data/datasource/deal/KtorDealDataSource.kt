package data.datasource.deal

import data.api.HOST_URL
import data.api.model.deal.DealResponse
import data.api.model.deal.game.GameWithDealsReponse
import data.api.useClient
import domain.deal.DealParams
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

private const val DEALS_PATH = "deals"
private const val PAGE_NUMBER_PARAMETER = "pageNumber"

private const val GAMES_PATH = "games"
private const val GAME_ID_PARAMETER = "id"

class KtorDealDataSource : DealDataSource {
    override suspend fun getDeals(params: DealParams): List<DealResponse> {
        val response = useClient { client ->
            client.get("$HOST_URL/$DEALS_PATH") {
                url {
                    parameters.append(PAGE_NUMBER_PARAMETER, params.pageNumber.toString())
                }
            }
        }
        return Json.decodeFromString<List<DealResponse>>(
            response.bodyAsText()
        )
    }

    override suspend fun getGameWithDeals(gameID: String): GameWithDealsReponse {
        val response = useClient { client ->
            client.get("$HOST_URL/$GAMES_PATH") {
                url {
                    parameters.append(GAME_ID_PARAMETER, gameID)
                }
            }
        }
        return Json.decodeFromString<GameWithDealsReponse>(
            response.bodyAsText()
        )
    }
}
