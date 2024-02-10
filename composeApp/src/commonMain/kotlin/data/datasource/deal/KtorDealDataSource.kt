package data.datasource.deal

import data.api.HOST_URL
import data.api.model.deal.DealResponse
import data.api.model.deal.game.GameWithDealsResponse
import data.api.useClient
import data.core.ApiResponse
import data.datasource.deal.params.toParam
import domain.deal.DealParams
import io.ktor.client.request.get

private const val DEALS_PATH = "deals"
private const val PAGE_NUMBER_PARAM = "pageNumber"
private const val SORTING_PARAM = "sortBy"
private const val QUERY_PARAM = "title"
private const val MAX_PRICE_PARAM = "upperPrice"
private const val ON_SALE_PARAM = "onSale"
private const val STORE_IDS_PARAM = "storeID"

private const val GAMES_PATH = "games"
private const val GAME_ID_PARAM = "id"

class KtorDealDataSource : DealDataSource {
    override suspend fun getDeals(params: DealParams): ApiResponse<List<DealResponse>> =
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
                    if (params.storeIds.isNotEmpty()) {
                        parameters.append(STORE_IDS_PARAM, params.storeIds.joinToString())
                    }
                }
            }
        }

    override suspend fun getGameWithDeals(gameID: String): ApiResponse<GameWithDealsResponse> =
        useClient { client ->
            client.get("$HOST_URL/$GAMES_PATH") {
                url {
                    parameters.append(GAME_ID_PARAM, gameID)
                }
            }
        }
}
