package com.itis.summerproject23

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(private val context: Context) {

    companion object {
        private const val PREF_NAME = "my_preferences"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USERNAME = "username"
    }

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getIsLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setIsLoggedIn(isLoggedIn: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        editor.apply()
    }

    fun getUserName(): String {
        return sharedPreferences.getString(KEY_USERNAME, "") ?: ""
    }

    fun setUserName(userName: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_USERNAME, userName)
        editor.apply()
    }

    fun clearUserData() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_IS_LOGGED_IN)
        editor.remove(KEY_USERNAME)
        editor.apply()
    }

    fun registerUser(isRegistered: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("is_registered", isRegistered)
        editor.apply()
    }
}