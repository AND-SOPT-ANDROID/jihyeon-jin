package org.sopt.and.ui.home.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.data.model.ContentType
import org.sopt.and.extension.noRippleClickable
import org.sopt.and.ui.theme.Gray3

@Composable
fun ContentKindRow(
    modifier: Modifier = Modifier,
    onContentTypeSelected: (ContentType) -> Unit
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ContentType.entries.forEach {
            Text(
                modifier = Modifier.noRippleClickable(onClick = { onContentTypeSelected(it) }),
                text = stringResource(it.titleResId),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Gray3
            )
        }
    }
}