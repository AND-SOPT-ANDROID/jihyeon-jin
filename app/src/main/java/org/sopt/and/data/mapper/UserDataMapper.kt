package org.sopt.and.data.mapper

import org.sopt.and.data.model.UserRegisterRequest
import org.sopt.and.domain.model.UserData

fun UserData.toRequestDto(): UserRegisterRequest {
    return UserRegisterRequest(
        username = this.username,
        password = this.password,
        hobby = this.hobby
    )
}