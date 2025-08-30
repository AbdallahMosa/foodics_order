package com.foodics.core.network

sealed class ApiException(message: String) : Exception(message) {
    data class NetworkError(val errorMessage: String) : ApiException(errorMessage)
    data class HttpError(val code: Int, override val message: String) :
        ApiException("HTTP $code: $message")

    data class SerializationError(val errorMessage: String) : ApiException(errorMessage)
    data class UnknownError(val errorMessage: String) : ApiException(errorMessage)
}