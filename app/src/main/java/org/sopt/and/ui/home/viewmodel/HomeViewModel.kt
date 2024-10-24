package org.sopt.and.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import org.sopt.and.R
import org.sopt.and.ui.home.state.HomeCommonContentState
import org.sopt.and.ui.home.state.HomeContentState

class HomeViewModel : ViewModel() {
    val mainContents: List<HomeContentState> = listOf(
        HomeContentState(
            id = 1,
            title = "이토록 친밀한 배신자",
            image = R.drawable.img_banner_poaster_1,
            description = "이토록 친밀한 배신자"
        ), HomeContentState(
            id = 1,
            title = "어이미남!!2",
            image = R.drawable.img_banner_poaster_2,
            description = "어이미남!!2"
        ), HomeContentState(
            id = 1,
            title = "나의 히어로 아카데미아",
            image = R.drawable.img_banner_poaster_3,
            description = "나의 히어로 아카데미아"
        )
    )
    val commonContents: List<HomeCommonContentState> = listOf(
        HomeCommonContentState(
            mainTitle = "믿고 보는 웨이브 에디터 추천작",
            contentStates = listOf(
                HomeContentState(
                    id = 1,
                    title = "로 앤 오더 : 토론토 - 크리미널 인텐드 시즌1",
                    image = R.drawable.thumbnail1,
                    description = "로 앤 오더 : 토론토 - 크리미널 인텐드 시즌1"
                ), HomeContentState(
                    id = 1,
                    title = "원피스",
                    image = R.drawable.thumbnail2,
                    description = "원피스"
                ), HomeContentState(
                    id = 1,
                    title = "이토록 친절한 배신자",
                    image = R.drawable.thumbnail3,
                    description = "이토록 친절한 배신자"
                ), HomeContentState(
                    id = 1,
                    title = "강철부대",
                    image = R.drawable.thumbnail4,
                    description = "강철부대"
                ), HomeContentState(
                    id = 1,
                    title = "지옥에서 온 판사",
                    image = R.drawable.thumbnail5,
                    description = "지옥에서 온 판사"
                )
            )
        ), HomeCommonContentState(
            mainTitle = "실시간 인기 콘텐츠",
            contentStates = listOf(
                HomeContentState(
                    id = 1,
                    title = "로 앤 오더 : 토론토 - 크리미널 인텐드 시즌1",
                    image = R.drawable.thumbnail1,
                    description = "로 앤 오더 : 토론토 - 크리미널 인텐드 시즌1"
                ), HomeContentState(
                    id = 1,
                    title = "원피스",
                    image = R.drawable.thumbnail2,
                    description = "원피스"
                ), HomeContentState(
                    id = 1,
                    title = "이토록 친절한 배신자",
                    image = R.drawable.thumbnail3,
                    description = "이토록 친절한 배신자"
                ), HomeContentState(
                    id = 1,
                    title = "강철부대",
                    image = R.drawable.thumbnail4,
                    description = "강철부대"
                ), HomeContentState(
                    id = 1,
                    title = "지옥에서 온 판사",
                    image = R.drawable.thumbnail5,
                    description = "지옥에서 온 판사"
                )
            ).reversed()
        ), HomeCommonContentState(
            mainTitle = "오직 웨이브에서",
            contentStates = listOf(
                HomeContentState(
                    id = 1,
                    title = "런닝맨",
                    image = R.drawable.thumbnail6,
                    description = "런닝맨"
                ), HomeContentState(
                    id = 1,
                    title = "미운 우리 새끼",
                    image = R.drawable.thumbnail7,
                    description = "미운 우리 새끼"
                ), HomeContentState(
                    id = 1,
                    title = "심야괴담회",
                    image = R.drawable.thumbnail8,
                    description = "심야괴담회"
                ), HomeContentState(
                    id = 1,
                    title = "나 혼자 산다",
                    image = R.drawable.thumbnail9,
                    description = "나 혼자 산다"
                ), HomeContentState(
                    id = 1,
                    title = "전지적 참견 시점",
                    image = R.drawable.thumbnail10,
                    description = "전지적 참견 시점"
                )
            )
        )
    )

    val rankingContents: HomeCommonContentState = HomeCommonContentState(
        mainTitle = "오늘의 TOP 20",
        contentStates = listOf(
            HomeContentState(
                id = 1,
                title = "로 앤 오더 : 토론토 - 크리미널 인텐드 시즌1",
                image = R.drawable.thumbnail1,
                description = "로 앤 오더 : 토론토 - 크리미널 인텐드 시즌1"
            ), HomeContentState(
                id = 1,
                title = "원피스",
                image = R.drawable.thumbnail2,
                description = "원피스"
            ), HomeContentState(
                id = 1,
                title = "이토록 친절한 배신자",
                image = R.drawable.thumbnail3,
                description = "이토록 친절한 배신자"
            ), HomeContentState(
                id = 1,
                title = "강철부대",
                image = R.drawable.thumbnail4,
                description = "강철부대"
            ), HomeContentState(
                id = 1,
                title = "지옥에서 온 판사",
                image = R.drawable.thumbnail5,
                description = "지옥에서 온 판사"
            ),
            HomeContentState(
                id = 1,
                title = "런닝맨",
                image = R.drawable.thumbnail6,
                description = "런닝맨"
            ), HomeContentState(
                id = 1,
                title = "미운 우리 새끼",
                image = R.drawable.thumbnail7,
                description = "미운 우리 새끼"
            ), HomeContentState(
                id = 1,
                title = "심야괴담회",
                image = R.drawable.thumbnail8,
                description = "심야괴담회"
            ), HomeContentState(
                id = 1,
                title = "나 혼자 산다",
                image = R.drawable.thumbnail9,
                description = "나 혼자 산다"
            ), HomeContentState(
                id = 1,
                title = "전지적 참견 시점",
                image = R.drawable.thumbnail10,
                description = "전지적 참견 시점"
            )
        )
    )
}