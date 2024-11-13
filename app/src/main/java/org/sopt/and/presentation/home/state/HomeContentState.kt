package org.sopt.and.presentation.home.state

import androidx.annotation.DrawableRes

data class HomeContentState(
    val id: Int,
    val title: String,
    @DrawableRes val image: Int,
    val description: String,
)