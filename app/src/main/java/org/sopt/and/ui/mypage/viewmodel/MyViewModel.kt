package org.sopt.and.ui.mypage.viewmodel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.sopt.and.utils.PreferenceUtils

class MyViewModel (
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    fun logOut(context: Context) {
        PreferenceUtils.clearAll(context)
    }
}