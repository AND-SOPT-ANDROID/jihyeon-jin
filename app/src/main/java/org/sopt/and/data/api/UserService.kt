package org.sopt.and.data.api

import org.sopt.and.data.model.UserRequestDto
import org.sopt.and.data.model.UserResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    suspend fun registerUser(@Body request: UserRequestDto): Response<UserResponseDto>
}