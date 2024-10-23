package org.sopt.and.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

data class ServiceAccountIconWithColor(
    val icon: Painter,
    val color: Color,
    val description: String
)
