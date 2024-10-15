package org.sopt.and.ui.sign.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.Gray3

@Composable
fun HelperText(
    isFieldFocused: Boolean,
    isValid: Boolean,
    value: String,
    invalidMessage: String,
    validMessage: String,
    minLines : Int = 2
) {
    val (helperText, helperColor, helperImage) = getHelperTextAndColor(
        isFieldFocused = isFieldFocused,
        isValid = isValid,
        value = value,
        invalidMessage = invalidMessage,
        validMessage = validMessage
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Image(
            painter = helperImage,
            contentDescription = stringResource(R.string.my_page_contents_image_description_no_content),
            modifier = Modifier.size(14.dp)
        )
        Text(
            text = helperText,
            color = helperColor,
            fontSize = 12.sp,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 18.sp,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Top,
                        trim = LineHeightStyle.Trim.FirstLineTop
                    )
                )
            ),
            minLines = minLines
        )
    }
}

@Composable
fun getHelperTextAndColor(
    isFieldFocused: Boolean,
    isValid: Boolean,
    value: String,
    invalidMessage: String,
    validMessage: String
): Triple<String, Color, Painter> {
    return if (value.isNotEmpty() && !isFieldFocused && !isValid) {
        Triple(
            invalidMessage,
            Color.Magenta,
            painterResource(R.drawable.ic_info_error_magenta)
        )
    } else {
        Triple(
            validMessage,
            Gray3,
            painterResource(R.drawable.ic_info_gray)
        )
    }
}