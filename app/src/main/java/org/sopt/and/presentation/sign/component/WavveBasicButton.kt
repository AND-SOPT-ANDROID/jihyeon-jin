package org.sopt.and.presentation.sign.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.core.designsystem.theme.WavvePrimary
import org.sopt.and.core.designsystem.theme.White

@Composable
fun WavveBasicButton(
    text: String,
    onClick : () -> Unit,
    modifier: Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = WavvePrimary
        ),
        onClick = onClick,
    ) {
        Text(
            text = text,
            color = White,
            fontSize = 16.sp,
            modifier = modifier.padding(vertical = 8.dp)
        )
    }
}