package data.datasource.deal

import data.api.HOST_URL
import data.api.model.deal.DealResponse
import data.api.model.deal.game.GameWithDealsResponse
import data.api.useClient
import data.datasource.deal.params.toParam
import domain.deal.DealParams
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.json.Json

private const val DEALS_PATH = "deals"
private const val PAGE_NUMBER_PARAM = "pageNumber"
private const val SORTING_PARAM = "sortBy"
private const val QUERY_PARAM = "title"
private const val MAX_PRICE_PARAM = "upperPrice"
private const val ON_SALE_PARAM = "onSale"

private const val GAMES_PATH = "games"
private const val GAME_ID_PARAM = "id"

class KtorDealDataSource : DealDataSource {
    override suspend fun getDeals(params: DealParams): List<DealResponse>? {
        val response = try {
            useClient { client ->
                client.get("$HOST_URL/$DEALS_PATH") {
                    url {
                        parameters.append(PAGE_NUMBER_PARAM, params.pageNumber.toString())
                        parameters.append(SORTING_PARAM, params.sortingType.toParam())
                        if (params.query.isNotBlank()) {
                            parameters.append(QUERY_PARAM, params.query)
                        }
                        if (params.maxPrice != null) {
                            parameters.append(MAX_PRICE_PARAM, params.maxPrice.toString())
                        }
                        if (params.onSale) {
                            parameters.append(ON_SALE_PARAM, params.onSale.toParam())
                        }
                    }
                }
            }
        } catch (e: UnresolvedAddressException) {
            null
        }
        return response?.bodyAsText()?.let { body ->
            Json.decodeFromString<List<DealResponse>>(body)
        }
    }

    override suspend fun getGameWithDeals(gameID: String): GameWithDealsResponse {
        val response = useClient { client ->
            client.get("$HOST_URL/$GAMES_PATH") {
                url {
                    parameters.append(GAME_ID_PARAM, gameID)
                }
            }
        }
        return Json.decodeFromString<GameWithDealsResponse>(
            response.bodyAsText()
        )
    }
}
