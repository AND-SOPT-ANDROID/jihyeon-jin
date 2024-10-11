package org.sopt.and

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import org.sopt.and.ui.mypage.MyActivity
import org.sopt.and.ui.sign.SignInActivity
import org.sopt.and.utils.PreferenceUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val id = PreferenceUtils.getUserId(this) ?:""
        val pw = PreferenceUtils.getUserPassword(this)?:""

        val intent = if (id.isNotBlank() && pw.isNotBlank()) {
            Intent(this, MyActivity::class.java)
        } else {
            Intent(this, SignInActivity::class.java)
        }

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}