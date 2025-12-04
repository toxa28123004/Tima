package com.example.tima

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tima.models.Characters
import com.example.tima.models.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object Routes {
    const val HOME = "home"
    const val CHARACTER = "character"
    const val PROFILE = "profile"
    const val SETTINGS = "setting"
    const val LOGIN = "login"
}

class MyViewModel(private val prefs: PreferenceManager) : ViewModel() {
    private val _state = MutableStateFlow(ScreenState())
    val state: StateFlow<ScreenState> = _state

    init {
        checkLogin()
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                val response: Characters = Retrofit.instance.getCharacter()
                _state.value = _state.value.copy(characters = response.results)
            } catch (e: Exception) {
                println("Error: $e")
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    fun onIntent(intent: ScreenIntent) {
        when (intent) {
            is ScreenIntent.EmailChanged -> updateEmail(intent.email)
            is ScreenIntent.PasswordChanged -> updatePassword(intent.password)
            is ScreenIntent.Register -> register()
            is ScreenIntent.Clear -> clear()
            is ScreenIntent.SaveCharacter -> saveCharacter(intent.result)
            is ScreenIntent.ReverseVisualPassword -> reverseVisualPassword()
            is ScreenIntent.GetEmail -> getEmail()
            is ScreenIntent.GetPassword -> getPassword()
            is ScreenIntent.DataClear -> clearFields()
        }
    }

    private fun saveCharacter(result: Result) {
        _state.value = _state.value.copy(person = result)
    }

    private fun checkLogin() {
        val isRegistered = prefs.getPassword().isNullOrEmpty()
        _state.value = _state.value.copy(isRegistered = isRegistered)
    }

    private fun updateEmail(email: String) {
        println("### UPDATE EMAIL = $email")
        _state.value = _state.value.copy(email = email, visibleEmail = email.isEmpty())
    }

    private fun updatePassword(password: String) {
        _state.value = _state.value.copy(password = password, visiblePassword = password.isEmpty())
    }

    private fun register() {
        if (_state.value.password.isNotEmpty() && _state.value.email.isNotEmpty()) {
            prefs.savePassword(_state.value.password)
            prefs.saveEmail(_state.value.email)
            _state.value = _state.value.copy(isNavigateToHome = !_state.value.isNavigateToHome)
        }
        val visibleEmail = _state.value.email.isEmpty()
        val visiblePassword = _state.value.password.isEmpty()
        showEmail(visibleEmail)
        showPassword(visiblePassword)
        clearFields()
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

    private fun reverseVisualPassword() {
        _state.value = _state.value.copy(isVisualPassword = !_state.value.isVisualPassword)
    }

    private fun getPassword() {
        val savePassword = prefs.getPassword()
        _state.value = _state.value.copy(password = savePassword ?: "")
    }

    private fun getEmail() {
        val saveEmail = prefs.getEmail()
        _state.value = _state.value.copy(email = saveEmail ?: "")
    }

}

