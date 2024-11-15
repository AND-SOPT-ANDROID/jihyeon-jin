package org.sopt.and.domain.model.entity

sealed class BaseResult<out T> {
    data class Success<out T>(val data: T) : BaseResult<T>()
    data class Error(
        val message: String,
        val errorCode: String? = null
    ) : BaseResult<Nothing>()
}