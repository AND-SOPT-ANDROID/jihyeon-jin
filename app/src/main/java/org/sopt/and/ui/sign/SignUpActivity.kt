package org.sopt.and.ui.sign

import android.app.Activity
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Gray3
import org.sopt.and.ui.theme.Gray4
import org.sopt.and.ui.theme.Gray5
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.ui.theme.WavveDisabled
import org.sopt.and.ui.theme.WavvePrimary
import java.util.regex.Pattern


class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val context = LocalContext.current
                val activity = context as? Activity
                Scaffold(
                    topBar = {
                        CloseTopBar("회원가입", { activity?.finish() })
                    }
                    , modifier = Modifier.background(WavveBg)
                ) { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WavveBg)
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

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

            WavveIDPWTextField(
                value = email,
                onValueChange = { email = it },
                hint = "wavve@example.com",
                isValid = isEmailValid
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해주세요.",
                color = Gray3,
                fontSize = 12.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            WavveIDPWTextField(
                value = password,
                onValueChange = { password = it },
                hint = "Wavve 비밀번호 설정",
                isPassword = true,
                isValid = isPasswordValid
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
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (isEmailValid && isPasswordValid) WavvePrimary else WavveDisabled
                )
                .padding(vertical = 14.dp)
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Wavve 회원가입",
                color = Color.White
            )
        }
    }
}

@Composable
fun ServiceAccountItemRow(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
    ) {
        for (type in 0..4) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(color = WavvePrimary)
            )
        }
    }
}
@Composable
fun WavveIDPWTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    isPassword: Boolean = false,
    isValid: Boolean,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }
    val borderColor = if (value.isNotEmpty() && !isFocused && !isValid) Color.Magenta else Color.Transparent
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                if (value.isEmpty()) {
                    Text(text = hint, color = WavveDisabled, fontSize = 16.sp)
                }
                innerTextField()
                if (isPassword)
                    Text(
                        text = if (passwordVisible) "hide" else "show",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable { passwordVisible = !passwordVisible }
                    )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()  // '완료' 버튼 클릭 시 포커스 해제
                keyboardController?.hide()  // 키보드 내리기
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Gray5,
                shape = RoundedCornerShape(7.dp),
            )
            .border(1.dp, borderColor, RoundedCornerShape(7.dp))
            .height(48.dp)
            .onFocusChanged {
                isFocused = it.isFocused
            }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CloseTopBar(title: String, onCloseClicked: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title, fontSize = 20.sp)
        },
        actions = {
            IconButton(onClick = onCloseClicked) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = WavveBg,
            titleContentColor = Color.White
        )
    )
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
@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    ANDANDROIDTheme {
        SignUpScreen()
    }
}