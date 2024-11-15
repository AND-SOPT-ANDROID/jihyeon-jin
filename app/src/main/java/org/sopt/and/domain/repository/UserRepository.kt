package org.sopt.and.domain.repository

import org.sopt.and.domain.model.BaseResult
import org.sopt.and.domain.model.UserData
import org.sopt.and.domain.model.UserResult

interface UserRepository {
    suspend fun registerUser(user : UserData): BaseResult<UserResult>
}