package org.sopt.and.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.extension.noRippleClickable
import org.sopt.and.ui.home.state.HomeCommonContentState
import org.sopt.and.ui.home.state.HomeContentState
import org.sopt.and.ui.theme.Gray3

@Composable
fun CommonContentHorizontalColumn (
    modifier: Modifier = Modifier,
    commonContentState: HomeCommonContentState,
    onContentClicked: (HomeContentState) -> Unit
) {
    val state = rememberLazyListState()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp - 32.dp

    Column(
        modifier = modifier
    ) {
        VideosKindDescriptionRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            kind = commonContentState.kind
        )
        LazyRow(
            modifier = Modifier.padding(top = 8.dp),
            state = state,
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
        ) {
            itemsIndexed(commonContentState.contentStates) { idx, _ ->
                CommonVideoItem(
                    modifier = Modifier.width(screenWidth / 3f - 6.dp),
                    commonContentState = commonContentState.contentStates[idx],
                    onClick = onContentClicked
                )
            }
        }
    }
}

@Composable
private fun VideosKindDescriptionRow(
    modifier: Modifier = Modifier,
    kind: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = kind,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = stringResource(R.string.home_icon_description_more),
            tint = Gray3,
            modifier = Modifier
                .size(24.dp)
        )
    }
}

@Composable
private fun CommonVideoItem(
    modifier: Modifier = Modifier,
    commonContentState: HomeContentState,
    onClick: (HomeContentState) -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .noRippleClickable { onClick(commonContentState) }
    ) {
        Image(
            painter = painterResource(commonContentState.image),
            contentDescription = commonContentState.description,
            modifier = Modifier
                .fillMaxSize()
                .height(174.dp),
            contentScale = ContentScale.Crop
        )
    }
}