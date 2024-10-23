package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.sopt.and.navigation.Screen
import org.sopt.and.ui.component.ScaffoldWithBottomNavigation
import org.sopt.and.ui.home.HomeScreen
import org.sopt.and.ui.home.viewmodel.HomeViewModel
import org.sopt.and.ui.mypage.MyScreen
import org.sopt.and.ui.sign.SignInScreen
import org.sopt.and.ui.sign.SignUpScreen
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.utils.PreferenceUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = PreferenceUtils.getUserId(this).orEmpty()
        val pw = PreferenceUtils.getUserPassword(this).orEmpty()

        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = WavveBg
            ) {
                val navController = rememberNavController()

                ScaffoldWithBottomNavigation(navController = navController) {
                    NavHost(
                        navController = navController,
                        startDestination = if (id.isBlank() || pw.isBlank()) Screen.SignInScreen(
                            "",
                            ""
                        ) else Screen.MyScreen
                    ) {
                        composable<Screen.SignInScreen> { backStackEntry ->
                            val signInScreen = backStackEntry.toRoute<Screen.SignInScreen>()
                            SignInScreen(
                                signIn = signInScreen,
                                navigateToMy = {
                                    navController.navigate(Screen.MyScreen)
                                },
                                navigateToSignUp = {
                                    navController.navigate(Screen.SignUpScreen)
                                }
                            )
                        }

                        composable<Screen.SignUpScreen> {
                            SignUpScreen(
                                navigateToSignIn = { email, password ->
                                    navController.navigate(Screen.SignInScreen(email, password))
                                }
                            )
                        }

                        composable<Screen.MyScreen> {
                            MyScreen(
                                navigateToSignIn = {
                                    navController.navigate(Screen.SignInScreen("", "")) {
                                        popUpTo(Screen.SignInScreen("", "")) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable<Screen.Home> {
                            val homeViewModel = HomeViewModel()
                            HomeScreen(
                                modifier = Modifier.background(WavveBg),
                                mainContentState = homeViewModel.mainContents,
                                commonContentState = homeViewModel.commonContents,
                                rankingContentState = homeViewModel.rankingContents,
                                onContentTypeSelected = { TODO() },
                            )
                        }
                    }
                }
            }
        }
    }
}