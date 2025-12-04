package com.example.tima

import android.content.Context
import androidx.core.content.edit

class PreferenceManager(context: Context) {

	private val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

	fun savePassword(password: String) {
		prefs.edit { putString(PASSWORD, password).apply() }
	}
    fun saveEmail(email: String){
        prefs.edit{putString(EMAIL, email).apply()}
    }

	fun getPassword(): String? {
		return prefs.getString(PASSWORD, null)
	}
    fun getEmail(): String?{
        return prefs.getString(EMAIL,null)
    }

	fun clear() {
		prefs.edit { clear().apply() }
	}

	companion object {
		const val PASSWORD = "password"
        const val EMAIL ="login"
	}
}
