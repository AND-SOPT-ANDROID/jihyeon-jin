package org.sopt.and.ui.sign.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.Gray5
import org.sopt.and.ui.theme.WavveDisabled

@Composable
fun SignUpIDTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "wavve@example.com",
    isValid: Boolean,
    onFocusChange: (Boolean) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val borderColor = if (value.isNotEmpty() && !isFocused && !isValid) Color.Magenta else Color.Transparent
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
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

            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
        ),
        singleLine = true,
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
                onFocusChange(isFocused)
            }
    )
}