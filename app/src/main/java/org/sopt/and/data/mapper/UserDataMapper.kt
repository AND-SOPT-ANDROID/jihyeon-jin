package org.sopt.and.data.mapper

import org.sopt.and.data.model.UserRequestDto
import org.sopt.and.domain.model.UserData

fun UserData.toRequestDto(): UserRequestDto {
    return UserRequestDto(
        username = this.username,
        password = this.password,
        hobby = this.hobby
    )
}