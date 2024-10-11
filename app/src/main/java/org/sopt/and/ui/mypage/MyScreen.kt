package org.sopt.and.ui.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.mypage.component.MyPageContents
import org.sopt.and.ui.mypage.component.MyPagePromotion
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.ui.theme.WavveDisabled

@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    email: String = "",
    onLogoutButtonPress: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WavveBg)
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    R.drawable.profile_default
                ),
                contentDescription = stringResource(R.string.my_page_image_description_profile),
                modifier = Modifier.size(54.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = "${email}님",
                color = Color.White,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = stringResource(R.string.my_page_icon_description_notification),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = stringResource(R.string.my_page_image_description_setting),
                tint = Color.White
            )
        }
        MyPagePromotion(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = WavveBg)
                .padding(16.dp),
            title = stringResource(R.string.my_page_text_promotion_month)
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Black
        )
        MyPagePromotion(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = WavveBg)
                .padding(16.dp),
            title = stringResource(R.string.my_page_text_no_ticket)
        )
        MyPageContents(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .background(Color.Black)
                .padding(top = 16.dp),
            title = stringResource(R.string.my_page_text_title_watch),
            information = stringResource(R.string.my_page_text_empty_watch),
        )
        MyPageContents(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .background(Color.Black)
                .padding(top = 16.dp),
            title = stringResource(R.string.my_page_text_title_interest),
            information = stringResource(R.string.my_page_text_empty_interest),
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(WavveDisabled)
                .wrapContentHeight()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onLogoutButtonPress()
                }
                .padding(vertical = 14.dp)
        ) {
            Text(
                text = stringResource(R.string.my_page_text_logout),
                color = Color.White
            )
        }
    }
}
