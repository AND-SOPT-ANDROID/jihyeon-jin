package org.sopt.and.ui.sign

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
                var textFieldEmail by remember {
                    mutableStateOf("")
                }
                 var textFieldPassword by remember {
                     mutableStateOf("")
                 }
                val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == RESULT_OK) {
                        val data = result.data
                        email = data?.getStringExtra("EMAIL")?:""
                        password = data?.getStringExtra("PASSWORD")?:""
                    }
                }
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                Scaffold(topBar = {
                    BackButtonTopBar({  })
                }, snackbarHost = { SnackbarHost(snackbarHostState) } ,
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignInScreen(
                        modifier = Modifier.padding(innerPadding),
                        context = context,
                        email = textFieldEmail,
                        onEmailChange = { textFieldEmail = it },
                        password = textFieldPassword,
                        onPasswordChange = { textFieldPassword = it },
                        onSignUpButtonClick = {
                            launcher.launch(Intent(this, SignUpActivity::class.java))
                        },
                        onSignInButtonClick = {
                            if (email == textFieldEmail && password == textFieldPassword) {
                                if (email.isNotBlank() && password.isNotBlank()) {
                                    PreferenceUtils.saveUserId(context, email)
                                    PreferenceUtils.saveUserPassword(context, password)
                                    val intent = Intent(context, MyActivity::class.java).apply {
                                        putExtra("SNACKBAR_MESSAGE",
                                            getString(R.string.sign_in_snackbar_login_success))
                                    }
                                    startActivity(intent)
                                    finish()
                                }
                                else{
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = getString(R.string.sign_in_snackbar_enter_id_password),
                                            actionLabel = getString(R.string.sign_in_snackbar_action_close)
                                        )
                                    }
                                }
                            } else {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = getString(R.string.sign_in_snackbar_error_id_pw),
                                        actionLabel = getString(R.string.sign_in_snackbar_action_close)
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}


