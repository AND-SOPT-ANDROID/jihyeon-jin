package org.sopt.and.domain.repository

import org.sopt.and.domain.model.entity.BaseResult
import org.sopt.and.domain.model.entity.UserData
import org.sopt.and.domain.model.entity.UserLoginResult

interface UserLoginRepository {
    suspend fun loginUser(user : UserData): BaseResult<UserLoginResult>
}