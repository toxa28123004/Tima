package com.example.tima

import com.example.tima.models.Result

sealed class ScreenIntent {
    data class EmailSave(val email: String) : ScreenIntent()
    data class PasswordSave(val password: String) : ScreenIntent()
    data object Register : ScreenIntent()
    data object Clear : ScreenIntent()
    data class SaveCharacter(val result: Result) : ScreenIntent()
    data object ReverseVisualPassword : ScreenIntent()
    data object GetEmail : ScreenIntent()
    data object GetPassword : ScreenIntent()
    data object DataClear : ScreenIntent()
    data object LoadCharacters : ScreenIntent()
    data class ChangePassword(val newPassword: String) : ScreenIntent()
    data class ChangePassword1(val newPassword1: String) : ScreenIntent()
    data class CheckPassword(val checkPassword: String) : ScreenIntent()
    data object SaveNewPassword : ScreenIntent()
    data object CheckEmptyOldPassword : ScreenIntent()
    data object CheckEquallyNewPasswords : ScreenIntent()


}