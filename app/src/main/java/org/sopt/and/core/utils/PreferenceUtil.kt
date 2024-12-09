package org.sopt.and.core.utils

import android.content.SharedPreferences
import androidx.compose.runtime.staticCompositionLocalOf
import javax.inject.Inject


class PreferenceUtil @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    companion object {
        private const val USER_TOKEN = "user_token"
        val LocalPreferenceUtils = staticCompositionLocalOf<PreferenceUtil> {
            error("PreferenceUtils is not initialized")
        }
    }

    fun saveUserToken(token: String) {
        sharedPreferences.edit().putString(USER_TOKEN, token).apply()
    }

    fun getUserToken(): String? {
        return sharedPreferences.getString(USER_TOKEN, null)
    }

    fun clearUserToken() {
        sharedPreferences.edit().remove(USER_TOKEN).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}
