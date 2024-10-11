package org.sopt.and.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceUtils {

    private const val PREFS_NAME = "wavve_prefs"
    private const val KEY_USER_ID = "key_user_id"
    private const val KEY_USER_PASSWORD = "key_user_password"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveUserId(context: Context, userId: String) {
        val prefs = getPreferences(context)
        prefs.edit().putString(KEY_USER_ID, userId).apply()
    }
    fun saveUserPassword(context: Context, userNickname: String) {
        val prefs = getPreferences(context)
        prefs.edit().putString(KEY_USER_PASSWORD, userNickname).apply()
    }

    fun getUserId(context: Context): String? {
        val prefs = getPreferences(context)
        return prefs.getString(KEY_USER_ID, null)
    }

    fun getUserPassword(context: Context): String? {
        val prefs = getPreferences(context)
        return prefs.getString(KEY_USER_PASSWORD, null)
    }

    fun clearAll(context: Context) {
        val prefs = getPreferences(context)
        prefs.edit().clear().apply()
    }
}