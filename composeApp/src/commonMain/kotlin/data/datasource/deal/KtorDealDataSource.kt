package data.datasource.deal

import data.api.HOST_URL
import data.api.model.deal.DealResponse
import data.api.useClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class KtorDealDataSource : DealDataSource {
    override suspend fun getDeals(): List<DealResponse> {
        val response = useClient { client ->
            client.get("$HOST_URL/deals")
        }
        return Json.decodeFromString<List<DealResponse>>(
            response.bodyAsText()
        )
    }
}
