package org.sopt.and.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterRequest(
    val username: String,
    val password: String,
    val hobby: String
)