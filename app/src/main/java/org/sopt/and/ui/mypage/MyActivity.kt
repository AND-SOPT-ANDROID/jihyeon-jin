package org.sopt.and.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import org.sopt.and.R
import org.sopt.and.ui.sign.SignInActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.utils.PreferenceUtils

class MyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val snackbarHostState = remember { SnackbarHostState() }
            val message = intent.getStringExtra("SNACKBAR_MESSAGE") ?: ""
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                }) { innerPadding ->
                    LaunchedEffect(message) {
                        if (message.isNotEmpty()) {
                            snackbarHostState.showSnackbar(message)
                        }
                    }
                    val id = PreferenceUtils.getUserId(LocalContext.current)
                    if (id != null) {
                        MyScreen(modifier = Modifier.padding(innerPadding), email = id, onLogoutButtonPress = {
                            PreferenceUtils.clearAll(context = context)
                            Toast.makeText(context,
                                getString(R.string.my_page_toast_success_logout), Toast.LENGTH_SHORT).show()
                            val intent = Intent(context, SignInActivity::class.java)
                            startActivity(intent)
                            finish()
                        })
                    }
                    else {
                        MyScreen(modifier = Modifier.padding(innerPadding), onLogoutButtonPress = {
                            val intent = Intent(context, SignInActivity::class.java).apply {
                                putExtra("SNACKBAR_MESSAGE", getString(R.string.my_page_toast_success_logout))
                            }
                            startActivity(intent)
                            finish()
                        })
                    }
                }
            }
        }
    }
}
