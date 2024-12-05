package org.sopt.and.domain.repository

import org.sopt.and.domain.model.entity.BaseResult
import org.sopt.and.domain.model.entity.GetMyHobbyResult


interface GetMyHobbyRepository {
    suspend fun getMyHobby(token : String): BaseResult<GetMyHobbyResult>
}