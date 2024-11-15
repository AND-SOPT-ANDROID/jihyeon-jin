package org.sopt.and.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterRequest(
    val username: String,
    val password: String,
    val hobby: String
)