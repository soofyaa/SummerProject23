package com.itis.summerproject23

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

    fun saveUserName(name: String) {
        val editor = sharedPreferences.edit()
        editor.putString("UserName", name)
        editor.apply()
    }

    fun getUserName(): String {
        return sharedPreferences.getString("UserName", "") ?: ""
    }
}