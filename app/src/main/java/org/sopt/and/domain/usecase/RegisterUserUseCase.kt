package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.entity.BaseResult
import org.sopt.and.domain.model.entity.UserData
import org.sopt.and.domain.model.entity.UserRegisterResult
import org.sopt.and.domain.repository.UserRegisterRepository
import javax.inject.Inject

//유즈케이스란 사용자가 하려는 행위 -> 여기서는 회원가입 (유저등록)
//Repository를 사용하지 않는 이유 -> Repository 변경 시에 코드 변경 최소화 가능, 직관적으로 사용,
class RegisterUserUseCase @Inject constructor(
    private val userRegisterRepository: UserRegisterRepository
) {
    //suspend operator fun invoke -> 클래스를 함수처럼 사용하게 함
    suspend operator fun invoke(user : UserData): BaseResult<UserRegisterResult> {
        return userRegisterRepository.registerUser(user)
    }
}