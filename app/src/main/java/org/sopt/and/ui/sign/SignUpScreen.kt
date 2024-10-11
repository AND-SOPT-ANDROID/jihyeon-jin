package org.sopt.and.ui.sign

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.component.ServiceAccountItemRow
import org.sopt.and.ui.component.SignUpIDTextField
import org.sopt.and.ui.component.SignUpPasswordField
import org.sopt.and.ui.theme.Gray3
import org.sopt.and.ui.theme.Gray4
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.ui.theme.WavveDisabled
import org.sopt.and.ui.theme.WavvePrimary
import java.util.regex.Pattern

@Composable
fun SignUpScreen(modifier: Modifier = Modifier,
                 context: Context,
                 email: String,
                 onEmailChange: (String) -> Unit,
                 password: String,
                 onPasswordChange: (String) -> Unit,
                 onSignUpButtonPress: () -> Unit)
{
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WavveBg)
    ) {

        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        val isEmailValid = pattern.matcher(email).matches()
        val isPasswordValid = isValidPassword(password)


        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("이메일과 비밀번호 ")
                    }
                    withStyle(style = SpanStyle(color = Gray3)) {
                        append("만으로\n")
                    }
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("Wavve를 즐길 수 ")
                    }
                    withStyle(style = SpanStyle(color = Gray3)) {
                        append("있어요!")
                    }
                },
                fontSize = 22.sp,
                lineHeight = 30.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            SignUpIDTextField(
                value = email,
                onValueChange = onEmailChange,
                isValid = isEmailValid
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해주세요.",
                color = Gray3,
                fontSize = 12.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            SignUpPasswordField(
                value = password,
                onValueChange = onPasswordChange,
                hint = "Wavve 비밀번호 설정",
                isValid = isPasswordValid,
            )



            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력해주세요.",
                color = Gray3,
                fontSize = 12.sp,
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
                    text = "또는 다른 서비스 계정으로 로그인",
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
                text = "SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다. 기존 POOQ 계정 또는 Wavve 계정과는 연동되지 않으니 이용에 참고하세요.",
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
                .clickable {
                    if(isEmailValid && isPasswordValid)
                    {
                        onSignUpButtonPress()
                    } else {
                        Toast.makeText(context, "이메일 혹은 비밀번호를 양식에 맞게 작성해주세요", Toast.LENGTH_SHORT).show()
                    } }
                .padding(vertical = 14.dp)
        ) {
            Text(
                text = "Wavve 회원가입",
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