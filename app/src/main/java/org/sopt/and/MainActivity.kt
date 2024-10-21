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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.sopt.and.navigation.Screen
import org.sopt.and.ui.mypage.MyScreen
import org.sopt.and.ui.sign.SignInScreen
import org.sopt.and.ui.sign.SignUpScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.utils.PreferenceUtils
import org.sopt.and.utils.SnackBarUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = PreferenceUtils.getUserId(this).orEmpty()
        val pw = PreferenceUtils.getUserPassword(this).orEmpty()

        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val snackbarHostState = remember { SnackbarHostState() }

                LaunchedEffect(Unit) {
                    SnackBarUtils.init(snackbarHostState)
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = if (id.isBlank() || pw.isBlank()) Screen.SignInScreen("", "") else Screen.MyScreen(id)
                    ) {
                        composable<Screen.SignInScreen> { backStackEntry ->
                            val signInScreen = backStackEntry.toRoute<Screen.SignInScreen>()
                            SignInScreen(
                                signIn = signInScreen,
                                navigateToMy = { email ->
                                    navController.navigate(Screen.MyScreen(email))
                                },
                                navigateToSignUp = {
                                    navController.navigate(Screen.SignUpScreen)
                                },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        composable<Screen.SignUpScreen> {
                            SignUpScreen(
                                navigateToSignIn = { email, password ->
                                    navController.navigate(Screen.SignInScreen(email, password))
                                },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        composable<Screen.MyScreen> { backStackEntry ->
                            val myScreen = backStackEntry.toRoute<Screen.MyScreen>()
                            MyScreen(
                                email = myScreen.email,
                                navigateToSignIn = {
                                    navController.navigate(Screen.SignInScreen("", "")){
                                        popUpTo(Screen.SignInScreen("", "")) { inclusive = true }
                                    }
                                },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}