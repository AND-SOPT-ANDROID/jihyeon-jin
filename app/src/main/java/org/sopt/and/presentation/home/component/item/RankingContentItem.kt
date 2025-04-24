package org.sopt.and.presentation.home.component.item

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
import org.sopt.and.core.extension.noRippleClickable
import org.sopt.and.domain.model.entity.HomeContent

@Composable
fun RankingContentItem(
    mainContentState: HomeContent,
    rank: Int,
    modifier: Modifier = Modifier,
    onClick: (HomeContent) -> Unit = {}
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