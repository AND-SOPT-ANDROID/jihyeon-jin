package org.sopt.and.ui.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.component.ServiceAccountItemRow
import org.sopt.and.ui.sign.component.HelperText
import org.sopt.and.ui.sign.component.SignUpIDTextField
import org.sopt.and.ui.sign.component.SignUpPasswordField
import org.sopt.and.ui.sign.viewmodel.SignUpViewModel
import org.sopt.and.ui.theme.Gray3
import org.sopt.and.ui.theme.Gray4
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.ui.theme.WavveDisabled
import org.sopt.and.ui.theme.WavvePrimary
import org.sopt.and.utils.showToast
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.extension.noRippleClickable
import org.sopt.and.ui.component.topBar.CloseTopBar
import org.sopt.and.ui.theme.White


@Composable
fun SignUpScreen(
    navigateToSignIn: (email: String, password: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel()) {

    val signUpState by viewModel.signUpState.collectAsState()
    val signUpSuccess by viewModel.signUpSuccess.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(signUpSuccess) {
        if (signUpSuccess) {
            context.showToast(context.getString(R.string.sign_up_toast_success))
            navigateToSignIn(signUpState.email, signUpState.password)
            viewModel.resetSignUpSuccess()
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WavveBg)
    ) {
        CloseTopBar(
            title = stringResource(R.string.sign_up_text_sign_up),
            onCloseClicked = {}
        )

        Column(modifier = Modifier.padding(16.dp)) {
            val textResource = stringResource(id = R.string.sign_up_text_welcome)
            val annotatedString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = White)) {
                    append(textResource.substring(0, 10)) // "이메일과 비밀번호 "
                }
                withStyle(style = SpanStyle(color = Gray3)) {
                    append(textResource.substring(10, 13)) // "만으로\n"
                }
                withStyle(style = SpanStyle(color = White)) {
                    append(textResource.substring(13, 25)) // "Wavve를 즐길 수 "
                }
                withStyle(style = SpanStyle(color = Gray3)) {
                    append(textResource.substring(25, 30)) // "있어요!"
                }
            }

            Text(
                text = annotatedString,
                fontSize = 22.sp,
                lineHeight = 30.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            SignUpIDTextField(
                value = signUpState.email,
                hint = stringResource(R.string.sign_up_text_field_hint_id),
                isValid = signUpState.isEmailValid,
                onFocusChange = { isFocused -> viewModel.updateEmailFieldFocused(isFocused)},
                onValueChange = { viewModel.updateEmail(it) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            HelperText(
                isFieldFocused = signUpState.isEmailFieldFocused,
                isValid = signUpState.isEmailValid,
                value = signUpState.email,
                invalidMessage = stringResource(R.string.sign_up_text_invalid_id),
                validMessage = stringResource(R.string.sign_up_text_valid_id)
            )

            Spacer(modifier = Modifier.height(8.dp))

            SignUpPasswordField(
                value = signUpState.password,
                onValueChange = { viewModel.updatePassword(it) },
                hint = stringResource(R.string.sign_up_text_field_hint_password),
                isValid = signUpState.isPasswordValid,
                onFocusChange = { isFocused -> viewModel.updatePasswordFieldFocused(isFocused) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            HelperText(
                isFieldFocused = signUpState.isPasswordFieldFocused,
                isValid = signUpState.isPasswordValid,
                value = signUpState.password,
                invalidMessage = stringResource(R.string.sign_up_text_invalid_password),
                validMessage = stringResource(R.string.sign_up_text_valid_password)
            )


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
                    text = stringResource(R.string.sign_up_text_other_service),
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
                text = stringResource(R.string.sign_up_text_information),
                color = Gray3,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

        }
        Spacer(Modifier.weight(1f))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (signUpState.isValid) WavvePrimary else WavveDisabled
                )
                .wrapContentHeight()
                .noRippleClickable { viewModel.signUp() }
                .padding(vertical = 14.dp)
        ) {
            Text(
                text = stringResource(R.string.sign_up_text_wavve_sign_up),
                color = White
            )
        }
    }
}