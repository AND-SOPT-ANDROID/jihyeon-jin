package org.sopt.and.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data class SignInScreen(
        val email: String,
        val password: String
    ) : Screen()

    @Serializable
    data object SignUpScreen : Screen()

    @Serializable
    data object MyScreen: Screen()

    @Serializable
    data object Home : Screen()
}