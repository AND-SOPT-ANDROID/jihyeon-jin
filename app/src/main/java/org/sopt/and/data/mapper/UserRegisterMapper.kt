package org.sopt.and.data.mapper

import org.sopt.and.data.model.request.UserRegisterRequest
import org.sopt.and.domain.model.entity.UserData

fun UserData.toRegisterRequestDto(): UserRegisterRequest {
    return UserRegisterRequest(
        username = this.username,
        password = this.password,
        hobby = this.hobby
    )
}