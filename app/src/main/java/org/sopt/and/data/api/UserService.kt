package org.sopt.and.data.api

import org.sopt.and.data.model.request.UserLoginRequest
import org.sopt.and.data.model.request.UserRegisterRequest
import org.sopt.and.data.model.response.UserLoginResponse
import org.sopt.and.data.model.response.UserRegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    suspend fun registerUser(@Body request: UserRegisterRequest): Response<UserRegisterResponse>
    @POST("/login")
    suspend fun loginUser(@Body request: UserLoginRequest): Response<UserLoginResponse>
}