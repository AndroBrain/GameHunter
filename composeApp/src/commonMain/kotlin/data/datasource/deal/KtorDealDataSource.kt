package data.datasource.deal

import data.api.HOST_URL
import data.api.model.deal.DealResponse
import data.api.useClient
import domain.deal.DealParams
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

private const val PAGE_NUMBER_PARAMETER = "pageNumber"

class KtorDealDataSource : DealDataSource {
    override suspend fun getDeals(params: DealParams): List<DealResponse> {
        delay(3000L)
        val response = useClient { client ->
            client.get("$HOST_URL/deals") {
                url {
                    parameters.append(PAGE_NUMBER_PARAMETER, params.pageNumber.toString())
                }
            }
        }
        return Json.decodeFromString<List<DealResponse>>(
            response.bodyAsText()
        )
    }
}
