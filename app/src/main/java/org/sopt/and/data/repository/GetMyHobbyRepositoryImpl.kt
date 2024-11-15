package org.sopt.and.data.repository

import kotlinx.serialization.json.Json
import org.sopt.and.data.api.UserService
import org.sopt.and.data.common.APICallType
import org.sopt.and.data.mapper.ErrorMapper
import org.sopt.and.data.model.BaseResponse
import org.sopt.and.data.model.ErrorResponse
import org.sopt.and.data.model.toBaseResult
import org.sopt.and.domain.model.entity.BaseResult
import org.sopt.and.domain.model.entity.GetMyHobbyResult
import org.sopt.and.domain.repository.GetMyHobbyRepository
import retrofit2.HttpException
import javax.inject.Inject

class GetMyHobbyRepositoryImpl @Inject constructor(
    private val userService: UserService
) : GetMyHobbyRepository {
    override suspend fun getMyHobby(token: String): BaseResult<GetMyHobbyResult> {
        val apiResult: BaseResponse<GetMyHobbyResult> = try {
            val response = userService.getMyHobby(token)
            if (response.isSuccessful) {
                response.body()?.result?.let {
                    BaseResponse.Success(GetMyHobbyResult(it.hobby))
                } ?: BaseResponse.Failure(null, null, "응답에 실패했습니다.")
            } else {
                val errorCode = response.errorBody()?.string()?.let { errorBodyString ->
                    try {
                        Json.decodeFromString<ErrorResponse>(errorBodyString).code
                    } catch (e: Exception) {
                        null
                    }
                }
                val errorMessage = ErrorMapper.getErrorMessage(
                    APICallType.GET_MY_HOBBY,
                    response.code(),
                    errorCode
                )
                BaseResponse.Failure(response.code(), errorCode, errorMessage)
            }
        } catch (e: HttpException) {
            val errorCode = e.response()?.errorBody()?.string()?.let { errorBodyString ->
                try {
                    Json.decodeFromString<ErrorResponse>(errorBodyString).code
                } catch (e: Exception) {
                    null
                }
            }
            val errorMessage = ErrorMapper.getErrorMessage(
                APICallType.GET_MY_HOBBY,
                e.response()?.code(),
                errorCode
            )
            BaseResponse.Failure(e.code(), errorCode, errorMessage)
        } catch (e: Exception) {
            BaseResponse.Failure(null, null, "네트워크 연결을 확인해주세요.")
        }

        return apiResult.toBaseResult()
    }
}