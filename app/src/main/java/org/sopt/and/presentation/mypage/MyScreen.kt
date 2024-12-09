package org.sopt.and.presentation.mypage

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.sopt.and.R
import org.sopt.and.core.extension.noRippleClickable
import org.sopt.and.presentation.mypage.component.MyPageContents
import org.sopt.and.presentation.mypage.component.MyPagePromotion
import org.sopt.and.presentation.mypage.viewmodel.MyViewModel
import org.sopt.and.core.designsystem.theme.Black
import org.sopt.and.core.designsystem.theme.WavveBg
import org.sopt.and.core.designsystem.theme.WavveDisabled
import org.sopt.and.core.designsystem.theme.White
import org.sopt.and.core.utils.SnackBarUtils

@Composable
fun MyScreen(
    navigateToSignIn : () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val myPageState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is MyPageContract.MyPageUiEffect.ShowErrorSnackBar -> {
                    SnackBarUtils.showSnackBar(
                        message = effect.message,
                        actionLabel = context.getString(R.string.sign_in_snackbar_action_close)
                    )
                }

                MyPageContract.MyPageUiEffect.NavigateToSignIn -> {
                    navigateToSignIn()
                }
            }
        }
    }

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
                text = myPageState.hobby,
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
                    viewModel.sendEvent(MyPageContract.MyPageUiEvent.Logout)
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
