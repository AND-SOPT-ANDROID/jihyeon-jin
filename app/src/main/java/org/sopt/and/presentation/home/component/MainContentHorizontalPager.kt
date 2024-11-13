package org.sopt.and.presentation.home.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.and.presentation.home.state.HomeContentState
import org.sopt.and.presentation.home.component.item.MainContentItem

@Composable
fun MainContentHorizontalPager(
    state: PagerState,
    mainContentState: List<HomeContentState>,
    onMainContentClicked: (HomeContentState) -> Unit,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        modifier = modifier,
        state = state,
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) { idx ->
        MainContentItem(
            modifier = Modifier.fillMaxSize(),
            mainContentState = mainContentState[idx % mainContentState.size], onClick = onMainContentClicked,
            totalPage = mainContentState.size,
            currentPage = idx % mainContentState.size + 1
        )
    }
}