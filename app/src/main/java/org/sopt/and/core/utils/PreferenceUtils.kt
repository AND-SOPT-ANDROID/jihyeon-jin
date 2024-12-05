package org.sopt.and.core.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceUtils {

    private const val PREFS_NAME = "wavve_prefs"
    private const val USER_TOKEN = "user_token"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveUserToken(context: Context, token: String) {
        val prefs = getPreferences(context)
        prefs.edit().putString(USER_TOKEN, token).apply()
    }
    fun getUserToken(context: Context): String? {
        val prefs = getPreferences(context)
        return prefs.getString(USER_TOKEN, null)
    }

    fun clearUserToken(context: Context) {
        val prefs = getPreferences(context)
        prefs.edit().remove(USER_TOKEN).apply()
    }
    fun clearAll(context: Context) {
        val prefs = getPreferences(context)
        prefs.edit().clear().apply()
    }
}