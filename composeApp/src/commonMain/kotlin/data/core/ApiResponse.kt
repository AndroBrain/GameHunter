package data.core

import domain.core.ResultErrorType
import io.ktor.client.statement.HttpResponse

sealed class ApiResponse<out T> {
    data class Ok<out T>(
        val value: T,
    ) : ApiResponse<T>()

    data class Error(
        val exception: ApiException,
    ) : ApiResponse<Nothing>() {
        fun toResultErrorType() =
            when (exception.response.status.value) {
//                401 -> ResultErrorType.UNAUTHORIZED
//                403 -> ResultErrorType.FORBIDDEN
//                404 -> ResultErrorType.NOT_FOUND
//                429 -> ResultErrorType.TOO_MANY_REQUESTS
//                500 -> ResultErrorType.SERVER
                else -> ResultErrorType.UNKNOWN
            }
    }

    data class NetworkError(
        val throwable: Throwable,
    ) : ApiResponse<Nothing>()
}

class ApiException(
    val response: HttpResponse,
) : Exception()
