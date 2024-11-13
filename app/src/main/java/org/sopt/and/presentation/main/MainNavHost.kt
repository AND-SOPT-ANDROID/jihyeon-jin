package org.sopt.and.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.sopt.and.domain.model.AuthConstants
import org.sopt.and.core.navigation.Screen
import org.sopt.and.presentation.home.HomeScreen
import org.sopt.and.presentation.mypage.MyScreen
import org.sopt.and.presentation.search.SearchScreen
import org.sopt.and.presentation.sign.SignInScreen
import org.sopt.and.presentation.sign.SignUpScreen
import org.sopt.and.core.designsystem.theme.WavveBg

@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: Screen
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable<Screen.SignIn> { backStackEntry ->
            val signInScreen = backStackEntry.toRoute<Screen.SignIn>()
            SignInScreen(
                signIn = signInScreen,
                navigateToMy = { navController.navigate(Screen.My) },
                navigateToSignUp = { navController.navigate(Screen.SignUp) }
            )
        }
        composable<Screen.SignUp> {
            SignUpScreen(
                navigateToSignIn = { email, password ->
                    navController.navigate(Screen.SignIn(email, password)) {
                        popUpTo<Screen.SignUp> { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Screen.My> {
            MyScreen(
                navigateToSignIn = {
                    navController.navigate(Screen.SignIn(AuthConstants.EMPTY_EMAIL, AuthConstants.EMPTY_PASSWORD)) {
                        popUpTo<Screen.My> { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Screen.Home> {
            HomeScreen(
                onContentTypeSelected = { /* TODO: Screen 변경 가능 */ },
                modifier = Modifier.background(WavveBg)
            )
        }
        composable<Screen.Search> {
            SearchScreen(modifier = Modifier.background(WavveBg))
        }
    }
}
