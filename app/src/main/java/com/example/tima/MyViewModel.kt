package com.example.tima

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel(private val prefs: PreferenceManager) : ViewModel() {
	private val _state = MutableStateFlow(ScreenState())
	val state: StateFlow<ScreenState> = _state

	init {
		checkLogin()
	}

	fun onIntent(intent: LoginIntent) {
		when (intent) {
			is LoginIntent.EmailChanged -> updateEmail(intent.email)
			is LoginIntent.PasswordChanged -> updatePassword(intent.password)
			is LoginIntent.Register -> register()
			is LoginIntent.Clear -> clear()
		}
	}

	private fun checkLogin() {
		val isRegistered = prefs.getPassword().isNullOrEmpty()
		_state.value = _state.value.copy(isRegistered = isRegistered)
	}

	private fun updateEmail(email: String) {
		_state.value = _state.value.copy(email = email, visibleEmail = email.isEmpty())
	}

	private fun updatePassword(password: String) {
		_state.value = _state.value.copy(password = password, visiblePassword = password.isEmpty())
	}

	private fun register() {
		if (_state.value.password.isNotEmpty() && _state.value.email.isNotEmpty()) {
			prefs.savePassword(_state.value.password)
			_state.value = _state.value.copy(isNavigateToHome = true)
			clearFields()
		}
		val visibleEmail = _state.value.email.isEmpty()
		val visiblePassword = _state.value.password.isEmpty()
		showEmail(visibleEmail)
		showPassword(visiblePassword)

	}

	private fun showEmail(show: Boolean) {
		_state.value = _state.value.copy(visibleEmail = show)
	}

	private fun showPassword(show: Boolean) {
		_state.value = _state.value.copy(visiblePassword = show)
	}

	private fun clearFields() {
		_state.value = _state.value.copy(email = "", password = "")
	}

	private fun clear() = prefs.clear()
}
