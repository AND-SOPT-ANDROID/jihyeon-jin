package org.sopt.and.ui.home.component

import androidx.compose.runtime.Composable
import org.sopt.and.ui.home.state.HomeCommonContentState
import org.sopt.and.ui.home.state.HomeContentState
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.home.component.item.RankingContentItem
import org.sopt.and.ui.theme.White

@Composable
fun RankingContentHorizontalColumn (
    commonContentState: HomeCommonContentState,
    onContentClicked: (HomeContentState) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val rankResource : List<Int> = listOf(
        R.drawable.img_rank_1,
        R.drawable.img_rank_2,
        R.drawable.img_rank_3,
        R.drawable.img_rank_4,
        R.drawable.img_rank_5,
        R.drawable.img_rank_6,
        R.drawable.img_rank_7,
        R.drawable.img_rank_8,
        R.drawable.img_rank_9,
        R.drawable.img_rank_10,
        R.drawable.img_rank_11,
        R.drawable.img_rank_12,
        R.drawable.img_rank_13,
        R.drawable.img_rank_14,
        R.drawable.img_rank_15,
        R.drawable.img_rank_16,
        R.drawable.img_rank_17,
        R.drawable.img_rank_18,
        R.drawable.img_rank_19,
        R.drawable.img_rank_20
    )
    Column(
        modifier = modifier
    ) {
        Text(
            text = commonContentState.mainTitle,
            fontSize = 18.sp,
            color = White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        LazyRow(
            modifier = Modifier.padding(top = 10.dp),
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            flingBehavior = rememberSnapFlingBehavior(
                lazyListState = lazyListState,
                snapPosition = SnapPosition.Start
            )
        ) {
            itemsIndexed(commonContentState.contentStates) { index, item ->
                RankingContentItem(
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp.dp / 2) - 28.dp),
                    mainContentState = item,
                    rank = rankResource[index],
                    onClick = onContentClicked
                )
            }
        }
    }
}
