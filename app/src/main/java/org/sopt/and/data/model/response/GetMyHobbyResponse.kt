package org.sopt.and.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class GetMyHobbyResponse(
    val result: ResultData?
) {
    @Serializable
    data class ResultData(val hobby: String)
}