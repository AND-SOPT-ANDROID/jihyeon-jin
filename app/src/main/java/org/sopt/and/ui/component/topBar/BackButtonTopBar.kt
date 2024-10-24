package org.sopt.and.ui.component.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackButtonTopBar(
    onBackButtonPress: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = WavveBg),
        title = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.wavve_logo),
                contentDescription = stringResource(R.string.back_button_top_bar_icon_description_logo),
                tint = White
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.back_button_top_bar_icon_description_back),
                tint = White,
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onBackButtonPress() }
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = WavveBg,
        )
    )
}