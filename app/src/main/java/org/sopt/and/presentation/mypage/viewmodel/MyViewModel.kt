package org.sopt.and.presentation.mypage.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import org.sopt.and.core.utils.PreferenceUtils

class MyViewModel : ViewModel() {

    fun logOut(context: Context) {
        PreferenceUtils.clearUserToken(context)
    }
}