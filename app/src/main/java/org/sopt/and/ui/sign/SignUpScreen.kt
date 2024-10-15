package org.sopt.and.ui.sign

import android.content.Context
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import org.sopt.and.ui.theme.Gray3
import org.sopt.and.ui.theme.Gray4
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.ui.theme.WavveDisabled
import org.sopt.and.ui.theme.WavvePrimary
import org.sopt.and.utils.showToast
import java.util.regex.Pattern

@Composable
fun SignUpScreen(
    context: Context,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUpButtonPress: () -> Unit,
    modifier: Modifier = Modifier)
{
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WavveBg)
    ) {

        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        val isEmailValid = pattern.matcher(email).matches()
        val isPasswordValid = isValidPassword(password)
        var isEmailFieldFocused by remember { mutableStateOf(false) }
        var isPasswordFieldFocused by remember { mutableStateOf(false) }

        Column(modifier = Modifier.padding(16.dp)) {
            val textResource = stringResource(id = R.string.sign_up_text_welcome)
            val annotatedString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White)) {
                    append(textResource.substring(0, 10)) // "이메일과 비밀번호 "
                }
                withStyle(style = SpanStyle(color = Gray3)) {
                    append(textResource.substring(10, 13)) // "만으로\n"
                }
                withStyle(style = SpanStyle(color = Color.White)) {
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
                value = email,
                hint =  stringResource(R.string.sign_up_text_field_hint_id),
                isValid = isEmailValid,
                onFocusChange = { isFocused -> isEmailFieldFocused = isFocused },
                onValueChange = onEmailChange
            )

            Spacer(modifier = Modifier.height(8.dp))

            HelperText(
                isFieldFocused = isEmailFieldFocused,
                isValid = isEmailValid,
                value = email,
                invalidMessage = stringResource(R.string.sign_up_text_invalid_id),
                validMessage = stringResource(R.string.sign_up_text_valid_id)
            )

            Spacer(modifier = Modifier.height(8.dp))

            SignUpPasswordField(
                value = password,
                onValueChange = onPasswordChange,
                hint = stringResource(R.string.sign_up_text_field_hint_password),
                isValid = isPasswordValid,
                onFocusChange = { isFocused -> isPasswordFieldFocused = isFocused }
            )

            Spacer(modifier = Modifier.height(8.dp))

            HelperText(
                isFieldFocused = isPasswordFieldFocused,
                isValid = isPasswordValid,
                value = password,
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
                    color = if (isEmailValid && isPasswordValid) WavvePrimary else WavveDisabled
                )
                .wrapContentHeight()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {

                    if (isEmailValid && isPasswordValid) {
                        context.showToast(context.getString(R.string.sign_up_toast_success))
                        onSignUpButtonPress()
                    } else {
                        context.showToast(context.getString(R.string.sign_up_toast_failed))
                    }
                }
                .padding(vertical = 14.dp)
        ) {
            Text(
                text = stringResource(R.string.sign_up_text_wavve_sign_up),
                color = Color.White
            )
        }
    }
}

fun isValidPassword(password: String): Boolean {
    val hasUpperCase = password.any { it.isUpperCase() }
    val hasLowerCase = password.any { it.isLowerCase() }
    val hasDigit = password.any { it.isDigit() }
    val hasSpecialChar = password.any { !it.isLetterOrDigit() }

    val lengthValid = password.length in 8..20
    val complexityValid = listOf(hasUpperCase, hasLowerCase, hasDigit, hasSpecialChar).count { it } >= 3

    return lengthValid && complexityValid
}