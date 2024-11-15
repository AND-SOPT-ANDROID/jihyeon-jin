package org.sopt.and.data.repository

import android.util.Log
import kotlinx.serialization.json.Json
import org.sopt.and.data.api.UserService
import org.sopt.and.data.common.APICallType
import org.sopt.and.data.mapper.ErrorMapper
import org.sopt.and.data.mapper.toRequestDto
import org.sopt.and.domain.model.UserData
import org.sopt.and.domain.model.UserResult
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.domain.model.BaseResult
import org.sopt.and.domain.model.ErrorResponse
import retrofit2.HttpException
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {
    override suspend fun registerUser(user: UserData): BaseResult<UserResult> {
        return try {
            val response = userService.registerUser(user.toRequestDto())
            if (response.isSuccessful) {
                response.body()?.result?.let {
                    BaseResult.Success(UserResult(it.no))
                } ?: BaseResult.Failure(null, null, "응답에 실패했습니다.")
            } else {
                val errorCode = response.errorBody()?.string()?.let { errorBodyString ->
                    try {
                        Json.decodeFromString<ErrorResponse>(errorBodyString).code
                    } catch (e: Exception) {
                        null
                    }
                }
                val errorMessage = ErrorMapper.getErrorMessage(APICallType.REGISTER_USER, response.code(), errorCode)
                BaseResult.Failure(response.code(), errorCode, errorMessage)
            }

        } catch (e: HttpException) {
            val statusCode = e.code()
            val errorCode = e.response()?.errorBody()?.string()?.let { errorBodyString ->
                try {
                    Json.decodeFromString<ErrorResponse>(errorBodyString).code
                } catch (e: Exception) {
                    null
                }
            }
            val errorMessage = ErrorMapper.getErrorMessage(APICallType.REGISTER_USER, statusCode, errorCode)
            BaseResult.Failure(statusCode, errorCode, errorMessage)
        } catch (e: Exception) {
            BaseResult.Failure(null, null, "네트워크 연결을 확인해주세요.")
        }
    }
}