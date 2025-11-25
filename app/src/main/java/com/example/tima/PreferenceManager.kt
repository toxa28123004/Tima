package com.example.tima

import android.content.Context
import androidx.core.content.edit

class PreferenceManager(context: Context) {

    private val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    fun savePassword(password: String) {
        prefs.edit { putString(PASSWORD, password) }
    }

    fun getPassword(): String? {
        return prefs.getString(PASSWORD, null)
    }

    fun clear() {
        prefs.edit { clear() }
    }

    companion object{
        private var instance: PreferenceManager? = null
        const val PASSWORD = "password"

        fun getInstance (context: Context): PreferenceManager {
            if (instance == null) {
                instance = PreferenceManager(context.applicationContext)
            }
            return instance!!
        }
    }
}
