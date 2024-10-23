package org.sopt.and.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import org.sopt.and.ui.home.state.HomeCommonContentState
import org.sopt.and.ui.home.state.HomeContentState
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.extension.noRippleClickable

@Composable
fun RankingContentHorizontalColumn (
    commonContentState: HomeCommonContentState,
    onContentClicked: (HomeContentState) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp - 32.dp
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
            text = commonContentState.kind,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        LazyRow(
            modifier = Modifier.padding(top = 10.dp),
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            flingBehavior = rememberSnapFlingBehavior(
                lazyListState = lazyListState,
                snapPosition = SnapPosition.Start
            )
        ) {
            itemsIndexed(commonContentState.contentStates) { idx, _ ->
                TodayTopVideoItem(
                    modifier = Modifier.width(screenWidth / 2f - 6.dp),
                    mainContentState = commonContentState.contentStates[idx],
                    rank = rankResource[idx],
                    onClick = onContentClicked
                )
            }
        }
    }
}

@Composable
private fun TodayTopVideoItem(
    modifier: Modifier = Modifier,
    mainContentState: HomeContentState,
    rank: Int,
    onClick: (HomeContentState) -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .noRippleClickable { onClick(mainContentState) }
    ) {
        Image(
            painter = painterResource(mainContentState.image),
            contentDescription = mainContentState.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .height(320.dp)
                .padding(bottom = 36.dp),
        )
        Image(
            painter = painterResource(rank),
            contentDescription = mainContentState.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomStart)
        )
    }
}