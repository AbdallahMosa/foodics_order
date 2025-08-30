package network

import com.foodics.core.network.ApiException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
import java.net.UnknownHostException

suspend fun <T> makeRequest(
    request: suspend () -> T
): Result<T> {
    return try {
        val response = request()
        Result.success(response)
    } catch (e: Exception) {
        if (e is CancellationException || e is UnknownHostException) {
            Result.failure(e)
        } else {
            val apiException = when (e) {
                is IOException -> ApiException.NetworkError("Network error: ${e.message}")
                is SerializationException -> ApiException.SerializationError("Serialization error: ${e.message}")
                is ClientRequestException -> ApiException.HttpError(
                    e.response.status.value,
                    "Client error: ${e.message}"
                )

                is ServerResponseException -> ApiException.HttpError(
                    e.response.status.value,
                    "Server error: ${e.message}"
                )

                else -> ApiException.UnknownError("Unknown error: ${e.message ?: "No message"}")
            }
            Result.failure(apiException)
        }
    }
}