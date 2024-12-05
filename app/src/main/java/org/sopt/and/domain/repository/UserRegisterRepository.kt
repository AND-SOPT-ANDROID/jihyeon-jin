package org.sopt.and.domain.repository

import org.sopt.and.domain.model.entity.BaseResult
import org.sopt.and.domain.model.entity.UserData
import org.sopt.and.domain.model.entity.UserRegisterResult

interface UserRegisterRepository {
    suspend fun registerUser(user : UserData): BaseResult<UserRegisterResult>
}