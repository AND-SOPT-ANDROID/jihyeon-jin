package org.sopt.and.presentation.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.core.extension.noRippleClickable
import org.sopt.and.core.navigation.Screen
import org.sopt.and.core.component.ServiceAccountItemRow
import org.sopt.and.core.component.textField.WavveCommonPasswordField
import org.sopt.and.core.component.textField.WavveCommonTextField
import org.sopt.and.core.component.topBar.BackButtonTopBar
import org.sopt.and.presentation.sign.component.WavveBasicButton
import org.sopt.and.presentation.sign.viewmodel.SignInViewModel
import org.sopt.and.core.designsystem.theme.Gray3
import org.sopt.and.core.designsystem.theme.Gray4
import org.sopt.and.core.designsystem.theme.WavveBg
import org.sopt.and.core.designsystem.theme.WavvePrimary
import org.sopt.and.core.designsystem.theme.White
import org.sopt.and.core.utils.SnackBarUtils

@Composable
fun SignInScreen(
    signIn: Screen.SignIn,
    navigateToMy: () -> Unit,
    navigateToSignUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = viewModel(),
) {
    val signInState by viewModel.signInState.collectAsState()
    val signInSuccess by viewModel.signInSuccess.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(signInSuccess) {
        if (signInSuccess) {
            navigateToMy()
            viewModel.resetSignInSuccess()
        }
    }
    signInState.snackbarMessage?.let { message ->
        LaunchedEffect(message) {
            viewModel.clearSnackbarMessage() // 메시지 초기화
            CoroutineScope(Dispatchers.Main).launch {
                SnackBarUtils.showSnackBar(
                    message = message,
                    actionLabel = context.getString(R.string.sign_in_snackbar_action_close)
                )
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WavveBg)
    ) {
        BackButtonTopBar({ /*TODO : 뒤로가기처리*/ })
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(WavveBg)
                .padding(16.dp)
        ) {
            WavveCommonTextField(
                value = signInState.email,
                onValueChange = viewModel::updateEmail,
                hint = stringResource(R.string.sign_in_text_field_id_hint)
            )
            Spacer(modifier = Modifier.height(4.dp))
            WavveCommonPasswordField(
                value = signInState.password,
                onValueChange = viewModel::updatePassword,
                hint = stringResource(R.string.sign_in_text_field_password_hint)
            )

            Spacer(modifier = Modifier.height(24.dp))

            WavveBasicButton(
                text = stringResource(R.string.sign_in_text_login),
                onClick = {
                    viewModel.updateIsValid(signIn.email, signIn.password)
                    viewModel.signIn(
                        context = context,
                        emailInput = signIn.email,
                        passwordInput = signIn.password
                    ) },
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(R.string.sign_in_text_find_id),
                    fontSize = 12.sp,
                    color = Gray3,
                )

                Text(
                    text = stringResource(R.string.sign_in_text_divider),
                    fontSize = 12.sp,
                    color = Gray3,
                )

                Text(
                    text = stringResource(R.string.sign_in_text_password_reset),
                    fontSize = 12.sp,
                    color = Gray3,
                )

                Text(
                    text = stringResource(R.string.sign_in_text_divider),
                    fontSize = 12.sp,
                    color = Gray3,
                )

                Text(
                    text = stringResource(R.string.sign_in_text_sign_up),
                    fontSize = 12.sp,
                    color = Gray3,
                    modifier = Modifier
                        .noRippleClickable(navigateToSignUp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically

            ) {
                HorizontalDivider(
                    thickness = 0.5.dp,
                    color = Gray4,
                    modifier = Modifier
                        .weight(1f)
                )

                Text(
                    text = stringResource(R.string.sign_in_text_other_service),
                    color = Gray3,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                HorizontalDivider(
                    thickness = 0.5.dp,
                    color = Gray4,
                    modifier = Modifier
                        .weight(1f)
                )
            }

            ServiceAccountItemRow()

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.sign_in_text_information),
                color = Gray3,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

        }
    }
}