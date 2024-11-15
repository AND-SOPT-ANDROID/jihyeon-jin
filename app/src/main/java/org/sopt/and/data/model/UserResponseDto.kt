package org.sopt.and.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    val result: ResultData?
) {
    @Serializable
    data class ResultData(val no: Int?)
}