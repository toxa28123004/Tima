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
    const val CHANGE_PASSWORD = "changePassword"
}

class MyViewModel(private val prefs: PreferenceManager) : ViewModel() {
    private val _state = MutableStateFlow(ScreenState())
    val state: StateFlow<ScreenState> = _state
    private var hasMorePages = true

    init {
        checkLogin()
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                val response: Characters = Retrofit.api.getCharacter(_state.value.currentPage1)
                _state.value =
                    _state.value.copy(characters = response.results)
            } catch (e: Exception) {
                println("Error: $e")
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    private fun addCharacters() {
        viewModelScope.launch {
            try {
                val response: Characters = Retrofit.api.getCharacter(_state.value.currentPage)
                _state.value =
                    _state.value.copy(characters = _state.value.characters + response.results)
                hasMorePages = response.info.next != null
                _state.value = _state.value.copy(currentPage = _state.value.currentPage + 1)
            } catch (e: Exception) {
                println("Error: $e")
            }
        }
    }

    fun onIntent(intent: ScreenIntent) {
        when (intent) {
            is ScreenIntent.EmailSave -> updateEmail(intent.email)
            is ScreenIntent.PasswordSave -> updatePassword(intent.password)
            is ScreenIntent.Register -> register()
            is ScreenIntent.Clear -> clear()
            is ScreenIntent.SaveCharacter -> saveCharacter(intent.result)
            is ScreenIntent.ReverseVisualPassword -> reverseVisualPassword()
            is ScreenIntent.GetEmail -> getEmail()
            is ScreenIntent.GetPassword -> getPassword()
            is ScreenIntent.DataClear -> clearFields()
            is ScreenIntent.LoadCharacters -> addCharacters()
            is ScreenIntent.ChangePassword -> changePassword(intent.newPassword)
            is ScreenIntent.SaveNewPassword -> saveNewPassword()
            is ScreenIntent.ChangePassword1 -> changePassword1(intent.newPassword1)
            is ScreenIntent.CheckPassword -> checkPassword(intent.checkPassword)
            is ScreenIntent.CheckEmptyOldPassword -> checkEmptyOldPassword()
            is ScreenIntent.CheckEquallyNewPasswords -> checkEquallyNewPasswords()
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

    private fun clearChangeFields() {
        _state.value = _state.value.copy(
            oldPassword = "",
            newPassword = "",
            newPassword1 = ""
        )
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

    private fun changePassword(newPassword: String) {
        _state.value = _state.value.copy(newPassword = newPassword)
    }

    private fun changePassword1(newPassword1: String) {
        _state.value = _state.value.copy(newPassword1 = newPassword1)
    }

    private fun checkPassword(checkPassword: String) {
        _state.value = _state.value.copy(oldPassword = checkPassword)
    }

    private fun saveNewPassword() {
        _state.value = _state.value.copy(password = _state.value.newPassword)
        prefs.savePassword(_state.value.password)
        clearChangeFields()
    }

    private fun checkEmptyOldPassword() {
        val visiblePassword =
            _state.value.oldPassword.isEmpty() || _state.value.password != _state.value.oldPassword
        showPassword(visiblePassword)
    }

    private fun checkEquallyNewPasswords() {
        val visibleNewPassword =
            _state.value.newPassword != _state.value.newPassword1 || _state.value.newPassword1.isEmpty()
        showNewPassword(visibleNewPassword)
    }

    private fun showNewPassword(show: Boolean) {
        _state.value = _state.value.copy(visibleNewPassword = show)
    }

}

