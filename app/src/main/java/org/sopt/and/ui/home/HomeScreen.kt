package org.sopt.and.ui.home

import org.sopt.and.data.model.ContentType
import org.sopt.and.ui.home.state.HomeCommonContentState
import org.sopt.and.ui.home.state.HomeContentState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.sopt.and.ui.component.topBar.CommonTopBar
import org.sopt.and.ui.home.component.CommonContentHorizontalColumn
import org.sopt.and.ui.home.component.ContentKindRow
import org.sopt.and.ui.home.component.MainContentHorizontalPager
import org.sopt.and.ui.home.component.RankingContentHorizontalColumn
import org.sopt.and.ui.theme.WavveBg

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    mainContentState: List<HomeContentState>,
    commonContentState: List<HomeCommonContentState>,
    rankingContentState: HomeCommonContentState,
    onContentTypeSelected: (ContentType) -> Unit,
    modifier: Modifier = Modifier
) {

    val mainPagerState = rememberPagerState(initialPage = Int.MAX_VALUE / 2) {
        Int.MAX_VALUE
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        stickyHeader {
            ContentKindRow(
                modifier = Modifier
                    .background(WavveBg)
                    .padding(horizontal = 16.dp)
                    .padding(top = 4.dp, bottom = 12.dp),
                onContentTypeSelected = onContentTypeSelected
            )
        }

        item {
            MainContentHorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                state = mainPagerState,
                mainContentState = mainContentState,
                onMainContentClicked = { }
            )
        }

        items(count = commonContentState.size) {
            Spacer(modifier = Modifier.height(12.dp))
            CommonContentHorizontalColumn (
                modifier = Modifier.fillMaxWidth(),
                commonContentState = commonContentState[it],
                onContentClicked = { }
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
            RankingContentHorizontalColumn(
                modifier = Modifier.fillMaxWidth(),
                commonContentState = rankingContentState,
                onContentClicked = { }
            )
        }

        items(count = commonContentState.size) {
            Spacer(modifier = Modifier.height(12.dp))
            CommonContentHorizontalColumn (
                modifier = Modifier.fillMaxWidth(),
                commonContentState = commonContentState[it],
                onContentClicked = { }
            )
        }
    }

    LaunchedEffect(mainPagerState.currentPage) {
        launch {
            while(true) {
                delay(3000)
                withContext(NonCancellable) {
                    mainPagerState.animateScrollToPage(
                        page = mainPagerState.currentPage + 1,
                        animationSpec = spring(stiffness = Spring.StiffnessLow)
                    )
                }
            }
        }
    }
}