package org.sopt.and.ui.home.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.and.extension.noRippleClickable
import org.sopt.and.ui.home.state.HomeContentState

@Composable
fun RankingContentItem(
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