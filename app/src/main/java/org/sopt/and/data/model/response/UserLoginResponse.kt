package org.sopt.and.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginResponse(
    val result: ResultData?
) {
    @Serializable
    data class ResultData(val token: String)
}