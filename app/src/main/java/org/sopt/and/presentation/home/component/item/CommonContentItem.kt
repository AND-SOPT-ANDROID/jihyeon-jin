package org.sopt.and.presentation.home.component.item

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
import org.sopt.and.core.extension.noRippleClickable
import org.sopt.and.domain.model.entity.HomeContent

@Composable
fun CommonContentItem(
    commonContentState: HomeContent,
    modifier: Modifier = Modifier,
    onClick: (HomeContent) -> Unit = {}
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