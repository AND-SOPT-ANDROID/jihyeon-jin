package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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
import org.sopt.and.ui.mypage.MyScreen
import org.sopt.and.ui.search.SearchScreen
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
                        startDestination = if (id.isBlank() || pw.isBlank()) Screen.SignIn(
                            "",
                            ""
                        ) else Screen.My,
                        enterTransition = {
                            EnterTransition.None
                        },
                        exitTransition = {
                            ExitTransition.None
                        },
                        popEnterTransition = {
                            EnterTransition.None
                        },
                        popExitTransition = {
                            ExitTransition.None
                        },
                    ) {
                        composable<Screen.SignIn> { backStackEntry ->
                            val signInScreen = backStackEntry.toRoute<Screen.SignIn>()
                            SignInScreen(
                                signIn = signInScreen,
                                navigateToMy = {
                                    navController.navigate(Screen.My)
                                },
                                navigateToSignUp = {
                                    navController.navigate(Screen.SignUp)
                                }
                            )
                        }

                        composable<Screen.SignUp> {
                            SignUpScreen(
                                navigateToSignIn = { email, password ->
                                    navController.navigate(Screen.SignIn(email, password))
                                    {
                                        popUpTo<Screen.SignUp>{ inclusive = true }
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable<Screen.My> {
                            MyScreen(
                                navigateToSignIn = {
                                    navController.navigate(Screen.SignIn("", "")) {
                                        popUpTo<Screen.My>{ inclusive = true }
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }
                        composable<Screen.Home> {
                            HomeScreen(
                                onContentTypeSelected = {
                                    /* TODO : type에 따라 Screen 변경 가능*/
                                    /*contentType ->
                                    when (contentType) {
                                        ContentType.MOVIE -> navController.navigate(Screen.MovieScreen)
                                        ContentType.DRAMA -> navController.navigate(Screen.DramaScreen)
                                        else -> {
                                        }
                                    }*/
                                },
                                modifier = Modifier.background(WavveBg)
                            )
                        }
                        composable<Screen.Search> {
                            SearchScreen(modifier = Modifier.background(WavveBg))
                        }
                    }
                }
            }
        }
    }
}