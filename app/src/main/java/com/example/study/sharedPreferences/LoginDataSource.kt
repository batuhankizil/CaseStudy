package com.example.study.sharedPreferences

import android.content.SharedPreferences
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_NAME = "username"
    }

    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) {
            sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()
        }

    var userName: String?
        get() = sharedPreferences.getString(KEY_USER_NAME, null)
        set(value) {
            sharedPreferences.edit().putString(KEY_USER_NAME, value).apply()
        }

    fun logout() {
        sharedPreferences.edit().clear().apply()
    }
}