package data.api

import data.core.ApiException
import data.core.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

const val HOST_URL = "https://www.cheapshark.com/api/1.0"

suspend inline fun <reified T> useClient(crossinline block: suspend (HttpClient) -> HttpResponse): ApiResponse<T> =
    withContext(Dispatchers.IO) {
        try {
            val client = HttpClient(CIO)
            val response = block(client)
            client.close()
            if (response.status.value > 299) {
                return@withContext ApiResponse.Error(
                    ApiException(response = response)
                )
            }
            return@withContext ApiResponse.Ok(Json.decodeFromString<T>(response.bodyAsText()))
        } catch (e: UnresolvedAddressException) {
            return@withContext ApiResponse.NetworkError(e)
        }
    }
