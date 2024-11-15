package org.sopt.and.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterResponse(
    val result: ResultData?
) {
    @Serializable
    data class ResultData(val no: Int?)
}