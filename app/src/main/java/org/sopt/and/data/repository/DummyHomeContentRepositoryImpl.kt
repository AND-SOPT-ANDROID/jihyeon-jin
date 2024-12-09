package org.sopt.and.data.repository

import org.sopt.and.R
import org.sopt.and.domain.model.entity.HomeCommonContent
import org.sopt.and.domain.model.entity.HomeContent
import org.sopt.and.domain.repository.DummyHomeContentRepository
import javax.inject.Inject

class DummyHomeContentRepositoryImpl @Inject constructor() : DummyHomeContentRepository {
    override fun getDummyMainContents(): List<HomeContent> = listOf(
        HomeContent(
            id = 1,
            title = "이토록 친밀한 배신자",
            image = R.drawable.img_banner_poaster_1,
            description = "이토록 친밀한 배신자"
        ), HomeContent(
            id = 1,
            title = "어이미남!!2",
            image = R.drawable.img_banner_poaster_2,
            description = "어이미남!!2"
        ), HomeContent(
            id = 1,
            title = "나의 히어로 아카데미아",
            image = R.drawable.img_banner_poaster_3,
            description = "나의 히어로 아카데미아"
        )
    )

    override fun getDummyCommonContents(): List<HomeCommonContent> = listOf(
        HomeCommonContent(
            mainTitle = "믿고 보는 웨이브 에디터 추천작",
            contentStates = listOf(
                HomeContent(
                    id = 1,
                    title = "로 앤 오더 : 토론토 - 크리미널 인텐드 시즌1",
                    image = R.drawable.thumbnail1,
                    description = "로 앤 오더 : 토론토 - 크리미널 인텐드 시즌1"
                ), HomeContent(
                    id = 1,
                    title = "원피스",
                    image = R.drawable.thumbnail2,
                    description = "원피스"
                ), HomeContent(
                    id = 1,
                    title = "이토록 친절한 배신자",
                    image = R.drawable.thumbnail3,
                    description = "이토록 친절한 배신자"
                ), HomeContent(
                    id = 1,
                    title = "강철부대",
                    image = R.drawable.thumbnail4,
                    description = "강철부대"
                ), HomeContent(
                    id = 1,
                    title = "지옥에서 온 판사",
                    image = R.drawable.thumbnail5,
                    description = "지옥에서 온 판사"
                )
            )
        ), HomeCommonContent(
            mainTitle = "실시간 인기 콘텐츠",
            contentStates = listOf(
                HomeContent(
                    id = 1,
                    title = "로 앤 오더 : 토론토 - 크리미널 인텐드 시즌1",
                    image = R.drawable.thumbnail1,
                    description = "로 앤 오더 : 토론토 - 크리미널 인텐드 시즌1"
                ), HomeContent(
                    id = 1,
                    title = "원피스",
                    image = R.drawable.thumbnail2,
                    description = "원피스"
                ), HomeContent(
                    id = 1,
                    title = "이토록 친절한 배신자",
                    image = R.drawable.thumbnail3,
                    description = "이토록 친절한 배신자"
                ), HomeContent(
                    id = 1,
                    title = "강철부대",
                    image = R.drawable.thumbnail4,
                    description = "강철부대"
                ), HomeContent(
                    id = 1,
                    title = "지옥에서 온 판사",
                    image = R.drawable.thumbnail5,
                    description = "지옥에서 온 판사"
                )
            ).reversed()
        ), HomeCommonContent(
            mainTitle = "오직 웨이브에서",
            contentStates = listOf(
                HomeContent(
                    id = 1,
                    title = "런닝맨",
                    image = R.drawable.thumbnail6,
                    description = "런닝맨"
                ), HomeContent(
                    id = 1,
                    title = "미운 우리 새끼",
                    image = R.drawable.thumbnail7,
                    description = "미운 우리 새끼"
                ), HomeContent(
                    id = 1,
                    title = "심야괴담회",
                    image = R.drawable.thumbnail8,
                    description = "심야괴담회"
                ), HomeContent(
                    id = 1,
                    title = "나 혼자 산다",
                    image = R.drawable.thumbnail9,
                    description = "나 혼자 산다"
                ), HomeContent(
                    id = 1,
                    title = "전지적 참견 시점",
                    image = R.drawable.thumbnail10,
                    description = "전지적 참견 시점"
                )
            )
        )
    )

    override fun getDummyRankingContents(): HomeCommonContent =
        HomeCommonContent(
            mainTitle = "오늘의 TOP 20",
            contentStates = listOf(
                HomeContent(
                    id = 1,
                    title = "로 앤 오더 : 토론토 - 크리미널 인텐드 시즌1",
                    image = R.drawable.thumbnail1,
                    description = "로 앤 오더 : 토론토 - 크리미널 인텐드 시즌1"
                ), HomeContent(
                    id = 1,
                    title = "원피스",
                    image = R.drawable.thumbnail2,
                    description = "원피스"
                ), HomeContent(
                    id = 1,
                    title = "이토록 친절한 배신자",
                    image = R.drawable.thumbnail3,
                    description = "이토록 친절한 배신자"
                ), HomeContent(
                    id = 1,
                    title = "강철부대",
                    image = R.drawable.thumbnail4,
                    description = "강철부대"
                ), HomeContent(
                    id = 1,
                    title = "지옥에서 온 판사",
                    image = R.drawable.thumbnail5,
                    description = "지옥에서 온 판사"
                ),
                HomeContent(
                    id = 1,
                    title = "런닝맨",
                    image = R.drawable.thumbnail6,
                    description = "런닝맨"
                ), HomeContent(
                    id = 1,
                    title = "미운 우리 새끼",
                    image = R.drawable.thumbnail7,
                    description = "미운 우리 새끼"
                ), HomeContent(
                    id = 1,
                    title = "심야괴담회",
                    image = R.drawable.thumbnail8,
                    description = "심야괴담회"
                ), HomeContent(
                    id = 1,
                    title = "나 혼자 산다",
                    image = R.drawable.thumbnail9,
                    description = "나 혼자 산다"
                ), HomeContent(
                    id = 1,
                    title = "전지적 참견 시점",
                    image = R.drawable.thumbnail10,
                    description = "전지적 참견 시점"
                )
            )
        )
}