package org.sopt.and.ui.home

import org.sopt.and.data.model.ContentType
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.sopt.and.ui.component.topBar.CommonTopBar
import org.sopt.and.ui.home.component.CommonContentHorizontalColumn
import org.sopt.and.ui.home.component.ContentTypeRow
import org.sopt.and.ui.home.component.MainContentHorizontalPager
import org.sopt.and.ui.home.component.RankingContentHorizontalColumn
import org.sopt.and.ui.home.viewmodel.HomeViewModel
import org.sopt.and.ui.theme.WavveBg

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onContentTypeSelected: (ContentType) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = HomeViewModel()
) {

    var selectedContentType by remember { mutableStateOf<ContentType?>(null) }
    val mainPagerState = rememberPagerState(initialPage = Int.MAX_VALUE / 2) {
        Int.MAX_VALUE // 페이지 수가 무한대
    }

    val (mainContentState, commonContentState, rankingContentState) = with(viewModel) {
        Triple(mainContents, commonContents, rankingContents)
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            CommonTopBar(
                onLiveButtonClick = {}
            )
        }
        stickyHeader {
            ContentTypeRow(
                modifier = Modifier
                    .background(WavveBg)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onContentTypeSelected = { contentType ->
                    selectedContentType = contentType
                    onContentTypeSelected(contentType)
                },
                selectedContentType = selectedContentType
            )
        }

        item {
            MainContentHorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp),
                state = mainPagerState,
                mainContentState = mainContentState,
                onMainContentClicked = { }
            )
        }

        items(commonContentState) { content ->
            CommonContentHorizontalColumn(
                commonContentState = content,
                onContentClicked = {  }
            )
        }

        item {
            RankingContentHorizontalColumn(
                modifier = Modifier.fillMaxWidth(),
                commonContentState = rankingContentState,
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