package org.sopt.and.ui.home.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.data.model.ContentType
import org.sopt.and.extension.noRippleClickable
import org.sopt.and.ui.theme.Gray3
import org.sopt.and.ui.theme.White

@Composable
fun ContentTypeRow(
    selectedContentType: ContentType?,
    onContentTypeSelected: (ContentType) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        ContentType.entries.forEach { contentType ->
            val isSelected = contentType == selectedContentType
            Text(
                modifier = Modifier
                    .noRippleClickable(onClick = { onContentTypeSelected(contentType) }),
                text = stringResource(contentType.titleResId),
                fontSize = 18.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) White else Gray3
            )
        }
    }
}