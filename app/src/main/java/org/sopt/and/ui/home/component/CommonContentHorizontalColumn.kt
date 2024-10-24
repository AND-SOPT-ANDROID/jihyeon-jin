package org.sopt.and.ui.home.component

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.home.component.item.CommonContentItem
import org.sopt.and.ui.home.state.HomeCommonContentState
import org.sopt.and.ui.home.state.HomeContentState
import org.sopt.and.ui.theme.Gray3
import org.sopt.and.ui.theme.White

@Composable
fun CommonContentHorizontalColumn (
    commonContentState: HomeCommonContentState,
    onContentClicked: (HomeContentState) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState()

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = commonContentState.mainTitle,
                fontSize = 18.sp,
                color = White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = stringResource(R.string.home_icon_description_more),
                tint = Gray3,
                modifier = Modifier.size(24.dp)
            )
        }
        LazyRow(
            modifier = Modifier.padding(top = 8.dp),
            state = state,
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
        ) {
            items(commonContentState.contentStates) { item ->
                CommonContentItem(
                    modifier = Modifier
                        .width((LocalConfiguration.current.screenWidthDp.dp / 3) - 16.dp)
                        .padding(horizontal = 3.dp),
                    commonContentState = item,
                    onClick = onContentClicked
                )
            }
        }
    }
}