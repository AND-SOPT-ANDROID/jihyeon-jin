package org.sopt.and.ui.home.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.extension.noRippleClickable
import org.sopt.and.ui.home.state.HomeContentState
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.ui.theme.WavveDisabled
import org.sopt.and.ui.theme.White

@Composable
fun MainContentItem(
    mainContentState: HomeContentState,
    totalPage: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
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
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
                .clip(RoundedCornerShape(50))
                .height(IntrinsicSize.Min)
                .background(WavveBg.copy(alpha = 0.9f))
                .padding(horizontal = 6.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = currentPage.toString(),
                fontSize = 12.sp,
                color = White
            )
            VerticalDivider(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp),
                thickness = 1.dp,
                color = WavveDisabled
            )
            Text(
                text = totalPage.toString(),
                fontSize = 12.sp,
                color = WavveDisabled
            )
        }
    }
}