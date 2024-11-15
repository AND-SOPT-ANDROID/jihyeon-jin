package org.sopt.and.data.model

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val code: String) : ApiResponse<Nothing>()
}