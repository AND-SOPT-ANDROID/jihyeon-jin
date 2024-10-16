package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.sopt.and.navigation.Screen
import org.sopt.and.ui.mypage.MyScreen
import org.sopt.and.ui.sign.SignIn
import org.sopt.and.ui.sign.SignInScreen
import org.sopt.and.ui.sign.SignUpScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.utils.PreferenceUtils
import org.sopt.and.utils.SnackBarUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = PreferenceUtils.getUserId(this) ?: ""
        val pw = PreferenceUtils.getUserPassword(this) ?: ""

        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val snackbarHostState = remember { SnackbarHostState() }

                LaunchedEffect(Unit) {
                    SnackBarUtils.init(snackbarHostState)
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = if (id.isBlank() || pw.isBlank()) Screen.SignInScreen.route else Screen.MyScreen.createRoute(id)
                    ) {
                        composable(route = Screen.SignInScreen.route) {
                            SignInScreen(
                                signIn = SignIn("", ""),
                                navigateToMy = { email ->
                                    navController.navigate(
                                        Screen.MyScreen.createRoute(
                                            email
                                        )
                                    )
                                },
                                navigateToSignUp = { navController.navigate(Screen.SignUpScreen.route) },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        composable(
                            route = "sign_in/{email}/{password}",
                            arguments = listOf(
                                navArgument("email") { type = NavType.StringType },
                                navArgument("password") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val email = backStackEntry.arguments?.getString("email").orEmpty()
                            val password = backStackEntry.arguments?.getString("password").orEmpty()
                            SignInScreen(
                                signIn = SignIn(email, password),
                                navigateToMy = {
                                    navController.navigate(
                                        Screen.MyScreen.createRoute(
                                            email
                                        )
                                    )
                                },
                                navigateToSignUp = { navController.navigate(Screen.SignUpScreen.route) },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        composable(route = Screen.SignUpScreen.route) {
                            SignUpScreen(
                                navigateToSignIn = { email, password ->
                                    navController.navigate(
                                        Screen.SignInScreen.createRoute(
                                            email,
                                            password
                                        )
                                    )
                                },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable(
                            route = Screen.MyScreen.route,
                            arguments = listOf(
                                navArgument("email") { type = NavType.StringType },
                            )
                        ) {
                            backStackEntry ->
                            val email = backStackEntry.arguments?.getString("email").orEmpty()
                            MyScreen(
                                email = email,
                                navigateToSignIn = { navController.navigate(Screen.SignInScreen.route) },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}