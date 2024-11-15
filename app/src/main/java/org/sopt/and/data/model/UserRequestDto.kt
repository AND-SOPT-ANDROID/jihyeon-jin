package org.sopt.and.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRequestDto(
    val username: String,
    val password: String,
    val hobby: String
)