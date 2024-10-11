package org.sopt.and.ui.sign

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.ui.component.topBar.BackButtonTopBar
import org.sopt.and.ui.mypage.MyActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.utils.PreferenceUtils

class SignInActivity : ComponentActivity() {
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val context = LocalContext.current
                var textFieldEmail by remember { mutableStateOf("") }
                var textFieldPassword by remember { mutableStateOf("") }

                val launcher = setupActivityResultLauncher()

                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                // 화면 구성
                Scaffold(
                    topBar = { BackButtonTopBar({ /*뒤로가기처리*/}) },
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    SignInScreen(
                        modifier = Modifier.padding(innerPadding),
                        email = textFieldEmail,
                        onEmailChange = { textFieldEmail = it },
                        password = textFieldPassword,
                        onPasswordChange = { textFieldPassword = it },
                        onSignUpButtonClick = {
                            launcher.launch(Intent(this, SignUpActivity::class.java))
                        },
                        onSignInButtonClick = {
                            handleSignIn(
                                context = context,
                                snackbarHostState = snackbarHostState,
                                scope = scope,
                                emailInput = textFieldEmail,
                                passwordInput = textFieldPassword
                            )
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun setupActivityResultLauncher() =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                email = data?.getStringExtra("EMAIL") ?: ""
                password = data?.getStringExtra("PASSWORD") ?: ""
            }
        }

    // 로그인 처리 함수
    private fun handleSignIn(
        context: Context,
        snackbarHostState: SnackbarHostState,
        scope: CoroutineScope,
        emailInput: String,
        passwordInput: String
    ) {
        if (email == emailInput && password == passwordInput) {
            if (emailInput.isNotBlank() && passwordInput.isNotBlank()) {
                PreferenceUtils.saveUserId(context, emailInput)
                PreferenceUtils.saveUserPassword(context, passwordInput)

                val intent = Intent(context, MyActivity::class.java).apply {
                    putExtra("SNACKBAR_MESSAGE", context.getString(R.string.sign_in_snackbar_login_success))
                }
                startActivity(intent)
                finish()
            } else {
                showSnackbar(
                    snackbarHostState = snackbarHostState,
                    scope = scope,
                    message = context.getString(R.string.sign_in_snackbar_enter_id_password)
                )
            }
        } else {
            showSnackbar(
                snackbarHostState = snackbarHostState,
                scope = scope,
                message = context.getString(R.string.sign_in_snackbar_error_id_pw)
            )
        }
    }

    private fun showSnackbar(
        snackbarHostState: SnackbarHostState,
        scope: CoroutineScope,
        message: String
    ) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = getString(R.string.sign_in_snackbar_action_close)
            )
        }
    }
}