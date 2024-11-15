package org.sopt.and.domain.model

import kotlinx.serialization.Serializable

@Serializable
sealed class BaseResult<out T> {
    data class Success<out T>(val data: T) : BaseResult<T>()
    data class Failure(
        val statusCode: Int?,
        val errorCode: String?,
        val message: String
    ) : BaseResult<Nothing>()
}
@Serializable
data class ErrorResponse(
    val code: String
)