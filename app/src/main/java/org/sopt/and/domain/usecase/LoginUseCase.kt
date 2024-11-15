package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.entity.BaseResult
import org.sopt.and.domain.model.entity.UserData
import org.sopt.and.domain.model.entity.UserLoginResult
import org.sopt.and.domain.repository.UserLoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userLoginRepository: UserLoginRepository
) {
    suspend operator fun invoke(user : UserData): BaseResult<UserLoginResult> {
        return userLoginRepository.loginUser(user)
    }
}