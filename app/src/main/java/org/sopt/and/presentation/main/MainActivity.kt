package org.sopt.and.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.core.composition.PreferenceUtilProvider
import org.sopt.and.core.utils.PreferenceUtil
import javax.inject.Inject

//AndroidEntryPoint : 의존성 주입을 사용할 Android 컴포넌트에 부착 -> 즉 DI 활성화
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var preferenceUtils: PreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PreferenceUtilProvider(preferenceUtil = preferenceUtils) {
                MainScreen()
            }
        }
    }
}