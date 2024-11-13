package org.sopt.and.ui.home.state

import androidx.annotation.DrawableRes

data class HomeContentState(
    val id: Int,
    val title: String,
    @DrawableRes val image: Int,
    val description: String,
)