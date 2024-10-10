package org.sopt.and.ui.sign.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.Gray5
import org.sopt.and.ui.theme.WavveDisabled

@Composable
fun SignUpPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    isValid: Boolean,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }
    val borderColor =
        if (value.isNotEmpty() && !isFocused && !isValid) Color.Magenta else Color.Transparent
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
        visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
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