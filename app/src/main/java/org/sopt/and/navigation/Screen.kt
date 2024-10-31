package org.sopt.and.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data class SignIn(
        val email: String,
        val password: String
    ) : Screen()

    @Serializable
    data object SignUp : Screen()

    @Serializable
    data object My: Screen()

    @Serializable
    data object Home : Screen()

    @Serializable
    data object Search : Screen()
}