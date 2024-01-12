package data.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

const val HOST_URL = "https://www.cheapshark.com/api/1.0"

suspend inline fun <reified T> useClient(crossinline block: suspend (HttpClient) -> T) =
    withContext(Dispatchers.IO) {
        val client = HttpClient(CIO)
        val result = block(client)
        client.close()
        return@withContext result
    }
