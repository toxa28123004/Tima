package com.example.tima

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class LoginIntent {
	data class EmailChanged(val email: String) : LoginIntent()
	data class PasswordChanged(val password: String) : LoginIntent()
	data object Register : LoginIntent()
}

data class ScreenState(
	val email: String = "",
	val password: String = ""
)

class MyViewModel(private val prefs: PreferenceManager) : ViewModel() {
	private val _state = MutableStateFlow(ScreenState())
	val state: StateFlow<ScreenState> = _state

	fun onIntent(intent: LoginIntent) {
		when (intent) {
			is LoginIntent.EmailChanged -> updateEmail(intent.email)
			is LoginIntent.PasswordChanged -> updatePassword(intent.password)
			is LoginIntent.Register -> register()
		}
	}

	private fun updateEmail(email: String) {
		_state.value = _state.value.copy(email = email)
	}

	private fun updatePassword(password: String) {
		_state.value = _state.value.copy(password = password)
	}

	private fun register() {
		if (_state.value.password.isNotEmpty() && _state.value.email.isNotEmpty()) {
			prefs.savePassword(_state.value.password)
		}
	}

}
