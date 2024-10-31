package org.sopt.and.ui.home.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.and.extension.noRippleClickable
import org.sopt.and.ui.home.state.HomeContentState

@Composable
fun CommonContentItem(
    commonContentState: HomeContentState,
    modifier: Modifier = Modifier,
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