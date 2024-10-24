package org.sopt.and.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.data.model.ServiceAccountIconWithColor
import org.sopt.and.ui.theme.AppleBg
import org.sopt.and.ui.theme.FacebookBg
import org.sopt.and.ui.theme.KaKaoBg
import org.sopt.and.ui.theme.NaverBg
import org.sopt.and.ui.theme.TBg

@Composable
fun ServiceAccountItemRow(
    modifier: Modifier = Modifier,
    items: List<ServiceAccountIconWithColor> = listOf(
        ServiceAccountIconWithColor(
            icon = painterResource(R.drawable.ic_kakao),
            color = KaKaoBg,
            description = stringResource(
                R.string.sign_image_description_kakao
            )
        ),
        ServiceAccountIconWithColor(
            icon = painterResource(R.drawable.ic_t),
            color = TBg,
            description = stringResource(
                R.string.sign_image_description_t
            )
        ),
        ServiceAccountIconWithColor(
            icon = painterResource(R.drawable.ic_naver),
            color = NaverBg,
            description = stringResource(
                R.string.sign_image_description_naver
            )
        ),
        ServiceAccountIconWithColor(
            icon = painterResource(R.drawable.ic_facebook),
            color = FacebookBg,
            description = stringResource(
                R.string.sign_image_description_facebook
            )
        ),
        ServiceAccountIconWithColor(
            icon = painterResource(R.drawable.ic_apple),
            color = AppleBg,
            description = stringResource(
                R.string.sign_image_description_apple
            )
        ),
    )
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
    )  {
        items.forEach { item ->
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(color = item.color),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = item.icon,
                    contentDescription =  item.description,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}