package org.sopt.and.data.mapper

import org.sopt.and.data.model.request.UserLoginRequest
import org.sopt.and.domain.model.entity.UserData

fun UserData.toUserLoginRequestDto(): UserLoginRequest = UserLoginRequest(
    username = this.username,
    password = this.password
)