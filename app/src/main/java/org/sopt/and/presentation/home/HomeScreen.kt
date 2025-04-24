package org.sopt.and.presentation.home

import org.sopt.and.core.ContentType
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.sopt.and.core.component.topBar.HomeTopBar
import org.sopt.and.presentation.home.component.CommonContentHorizontalColumn
import org.sopt.and.presentation.home.component.ContentTypeRow
import org.sopt.and.presentation.home.component.MainContentHorizontalPager
import org.sopt.and.presentation.home.component.RankingContentHorizontalColumn
import org.sopt.and.presentation.home.viewmodel.HomeViewModel
import org.sopt.and.core.designsystem.theme.WavveBg

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onContentTypeSelected: (ContentType) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeState by viewModel.uiState.collectAsStateWithLifecycle()
    val mainPagerState = rememberPagerState(initialPage = Int.MAX_VALUE / 2) {
        Int.MAX_VALUE // 페이지 수가 무한대
    }
    LaunchedEffect(Unit) {
        viewModel.getDummyHomeContent()
    }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            HomeTopBar(
                onLiveButtonClick = {}
            )
        }
        stickyHeader {
            ContentTypeRow(
                modifier = Modifier
                    .background(WavveBg)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onContentTypeSelected = { contentType ->
                    onContentTypeSelected(contentType)
                    viewModel.sendEvent(
                        HomeContract.HomeUiEvent.SetContentType(contentType)
                    )
                },
                selectedContentType = homeState.selectedContentType
            )
        }

        item {
            MainContentHorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp),
                state = mainPagerState,
                mainContent = homeState.mainContents,
                onMainContentClicked = { }
            )
        }

        items(homeState.commonContents) { content ->
            CommonContentHorizontalColumn(
                commonContent = content,
                onContentClicked = { }
            )
        }

        item {
            RankingContentHorizontalColumn(
                modifier = Modifier.fillMaxWidth(),
                commonContent = homeState.rankingContents,
                onContentClicked = { }
            )
        }
    }

    AutoScrollEffect(mainPagerState)
}


@Composable
fun AutoScrollEffect(pagerState: PagerState) {
    LaunchedEffect(pagerState.currentPage) {
        while (true) {
            delay(3000)
            withContext(NonCancellable) {
                pagerState.animateScrollToPage(
                    page = pagerState.currentPage + 1,
                    animationSpec = spring(stiffness = Spring.StiffnessLow)
                )
            }
        }
    }
}