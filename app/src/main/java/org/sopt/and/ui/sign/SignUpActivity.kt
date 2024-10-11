package org.sopt.and.ui.sign

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import org.sopt.and.R
import org.sopt.and.ui.component.topBar.CloseTopBar
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.utils.PreferenceUtils


class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val context = LocalContext.current
                val activity = context as? Activity
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                Scaffold(
                    topBar = {
                        CloseTopBar(stringResource(R.string.sign_up_text_sign_up), { activity?.finish() })
                    }
                    , modifier = Modifier.background(WavveBg)
                ) { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier.padding(innerPadding),
                        context = context,
                        email = email,
                        onEmailChange = { email = it },
                        password = password,
                        onPasswordChange = { password = it },
                        onSignUpButtonPress = {
                            intent.putExtra("EMAIL", email)
                            intent.putExtra("PASSWORD", password)
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                    )
                }
            }
        }
    }
}

