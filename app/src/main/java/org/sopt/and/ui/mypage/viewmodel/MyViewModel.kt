package org.sopt.and.ui.mypage.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import org.sopt.and.utils.PreferenceUtils

class MyViewModel : ViewModel() {

    fun logOut(context: Context) {
        PreferenceUtils.clearAll(context)
    }
}