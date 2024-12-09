package org.sopt.and.domain.repository

import org.sopt.and.domain.model.entity.HomeCommonContent
import org.sopt.and.domain.model.entity.HomeContent

interface DummyHomeContentRepository {
    fun getDummyMainContents(): List<HomeContent>
    fun getDummyCommonContents(): List<HomeCommonContent>
    fun getDummyRankingContents(): HomeCommonContent
}