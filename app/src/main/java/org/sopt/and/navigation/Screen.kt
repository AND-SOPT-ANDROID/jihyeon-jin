package org.sopt.and.navigation

sealed class Screen(val route: String) {
    data object SignInScreen : Screen("sign_in") {
        fun createRoute(email: String, password: String) = "sign_in/$email/$password"
    }

    data object SignUpScreen : Screen("sign_up")

    data object MyScreen : Screen("my/{email}"){
        fun createRoute(email: String) = "my/$email"
    }
}