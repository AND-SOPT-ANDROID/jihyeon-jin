package org.sopt.and.ui.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.extension.noRippleClickable
import org.sopt.and.ui.mypage.component.MyPageContents
import org.sopt.and.ui.mypage.component.MyPagePromotion
import org.sopt.and.ui.mypage.viewmodel.MyViewModel
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.ui.theme.WavveDisabled
import org.sopt.and.ui.theme.White
import org.sopt.and.utils.PreferenceUtils
import org.sopt.and.utils.showToast


@Composable
fun MyScreen(
    navigateToSignIn : () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyViewModel = viewModel()
) {
    val context = LocalContext.current
    val email = PreferenceUtils.getUserId(context)

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
                color = White
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = stringResource(R.string.my_page_icon_description_notification),
                tint = White
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = stringResource(R.string.my_page_image_description_setting),
                tint = White
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
            color = Black
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
                .background(Black)
                .padding(top = 16.dp),
            title = stringResource(R.string.my_page_text_title_watch),
            information = stringResource(R.string.my_page_text_empty_watch),
        )
        MyPageContents(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .background(Black)
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
                .noRippleClickable {
                    viewModel.logOut(context)
                    context.showToast(
                        context.getString(R.string.my_page_toast_success_logout)
                    )
                    navigateToSignIn()
                }
                .padding(vertical = 14.dp)
        ) {
            Text(
                text = stringResource(R.string.my_page_text_logout),
                color = White
            )
        }
    }
}
