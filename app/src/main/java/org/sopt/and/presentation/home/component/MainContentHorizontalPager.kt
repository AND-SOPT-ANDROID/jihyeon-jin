package org.sopt.and.presentation.home.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.and.domain.model.entity.HomeContent
import org.sopt.and.presentation.home.component.item.MainContentItem

@Composable
fun MainContentHorizontalPager(
    state: PagerState,
    mainContent: List<HomeContent>,
    onMainContentClicked: (HomeContent) -> Unit,
    modifier: Modifier = Modifier
) {
    if (mainContent.isNotEmpty()) {
        HorizontalPager(
            modifier = modifier,
            state = state,
            pageSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) { idx ->
            MainContentItem(
                modifier = Modifier.fillMaxSize(),
                mainContentState = mainContent[idx % mainContent.size],
                onClick = onMainContentClicked,
                totalPage = mainContent.size,
                currentPage = idx % mainContent.size + 1
            )
        }
    }
}