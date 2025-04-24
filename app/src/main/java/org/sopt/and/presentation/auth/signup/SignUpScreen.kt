package org.sopt.and.presentation.auth.signup

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.R
import org.sopt.and.core.component.ServiceAccountItemRow
import org.sopt.and.presentation.auth.signup.component.HelperText
import org.sopt.and.presentation.auth.signup.component.SignUpTextField
import org.sopt.and.presentation.auth.signup.component.SignUpPasswordField
import org.sopt.and.presentation.auth.signup.viewmodel.SignUpViewModel
import org.sopt.and.core.designsystem.theme.Gray3
import org.sopt.and.core.designsystem.theme.Gray4
import org.sopt.and.core.designsystem.theme.WavveBg
import org.sopt.and.core.designsystem.theme.WavveDisabled
import org.sopt.and.core.designsystem.theme.WavvePrimary
import org.sopt.and.core.utils.showToast
import org.sopt.and.core.extension.noRippleClickable
import org.sopt.and.core.component.topBar.CloseTopBar
import org.sopt.and.core.designsystem.theme.White


@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val signUpState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SignUpContract.SignUpUiEffect.ShowSuccessToast -> {
                    context.showToast(context.getString(R.string.sign_up_toast_success))
                    navigateToSignIn()
                }

                is SignUpContract.SignUpUiEffect.ShowErrorToast -> {
                    context.showToast(effect.message)
                }

                is SignUpContract.SignUpUiEffect.NavigateToSignIn -> navigateToSignIn()
                is SignUpContract.SignUpUiEffect.NavigateUp -> navigateUp()
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WavveBg)
    ) {
        CloseTopBar(
            title = stringResource(R.string.sign_up_text_sign_up),
            onCloseClicked = { viewModel.sendEvent(SignUpContract.SignUpUiEvent.Close) }
        )

        Column(modifier = Modifier.padding(16.dp)) {
            val textResource = stringResource(id = R.string.sign_up_text_welcome)
            val annotatedString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = White)) {
                    append(textResource.substring(0, 13)) // "이메일과 비밀번호, 취미 "
                }
                withStyle(style = SpanStyle(color = Gray3)) {
                    append(textResource.substring(13, 17)) // "만으로\n"
                }
                withStyle(style = SpanStyle(color = White)) {
                    append(textResource.substring(17, 29)) // "Wavve를 즐길 수 "
                }
                withStyle(style = SpanStyle(color = Gray3)) {
                    append(textResource.substring(29, 34)) // "있어요!"
                }
            }

            Text(
                text = annotatedString,
                fontSize = 22.sp,
                lineHeight = 30.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            SignUpTextField(
                value = signUpState.username,
                hint = stringResource(R.string.sign_up_text_field_hint_id),
                isValid = signUpState.isUserNameValid,
                onFocusChange = { isFocused ->
                    viewModel.sendEvent(
                        SignUpContract.SignUpUiEvent.UpdateFieldFocus(
                            SignUpContract.Field.UserName,
                            isFocused
                        )
                    )
                },
                onValueChange = {
                    viewModel.sendEvent(
                        SignUpContract.SignUpUiEvent.UpdateUserName(it)
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            HelperText(
                isFieldFocused = signUpState.isUserNameFieldFocused,
                isValid = signUpState.isUserNameValid,
                value = signUpState.username,
                invalidMessage = stringResource(R.string.sign_up_text_invalid_id),
                validMessage = stringResource(R.string.sign_up_text_valid_id)
            )

            Spacer(modifier = Modifier.height(8.dp))

            SignUpPasswordField(
                value = signUpState.password,
                hint = stringResource(R.string.sign_up_text_field_hint_password),
                isValid = signUpState.isPasswordValid,
                onFocusChange = { isFocused ->
                    viewModel.sendEvent(
                        SignUpContract.SignUpUiEvent.UpdateFieldFocus(
                            SignUpContract.Field.Password,
                            isFocused
                        )
                    )
                },
                onValueChange = {
                    viewModel.sendEvent(
                        SignUpContract.SignUpUiEvent.UpdatePassword(it)
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            HelperText(
                isFieldFocused = signUpState.isPasswordFieldFocused,
                isValid = signUpState.isPasswordValid,
                value = signUpState.password,
                invalidMessage = stringResource(R.string.sign_up_text_invalid_password),
                validMessage = stringResource(R.string.sign_up_text_valid_password)
            )

            Spacer(modifier = Modifier.height(8.dp))

            SignUpTextField(
                value = signUpState.hobby,
                hint = stringResource(R.string.sign_up_text_field_hint_hobby),
                isValid = signUpState.isHobbyValid,
                onFocusChange = { isFocused ->
                    viewModel.sendEvent(
                        SignUpContract.SignUpUiEvent.UpdateFieldFocus(
                            SignUpContract.Field.Hobby,
                            isFocused
                        )
                    )
                },
                onValueChange = {
                    viewModel.sendEvent(
                        SignUpContract.SignUpUiEvent.UpdateHobby(it)
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            HelperText(
                isFieldFocused = signUpState.isHobbyFieldFocused,
                isValid = signUpState.isHobbyValid,
                value = signUpState.hobby,
                invalidMessage = stringResource(R.string.sign_up_text_invalid_hobby),
                validMessage = stringResource(R.string.sign_up_text_valid_hobby)
            )

            Spacer(modifier = Modifier.height(8.dp))

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
                .noRippleClickable { viewModel.sendEvent(SignUpContract.SignUpUiEvent.SignUpFormSubmit) }
                .padding(vertical = 14.dp)
        ) {
            Text(
                text = stringResource(R.string.sign_up_text_wavve_sign_up),
                color = White
            )
        }
    }
}