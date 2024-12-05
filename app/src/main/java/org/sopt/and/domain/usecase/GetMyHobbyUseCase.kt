package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.entity.BaseResult
import org.sopt.and.domain.model.entity.GetMyHobbyResult
import org.sopt.and.domain.repository.GetMyHobbyRepository
import javax.inject.Inject

class GetMyHobbyUseCase @Inject constructor(
    private val getMyHobbyRepository: GetMyHobbyRepository
) {
    suspend operator fun invoke(token : String): BaseResult<GetMyHobbyResult> {
        return getMyHobbyRepository.getMyHobby(token)
    }
}