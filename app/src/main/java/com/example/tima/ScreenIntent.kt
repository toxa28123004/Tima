package com.example.tima

import com.example.tima.models.Result

sealed class ScreenIntent {
    data class EmailChanged(val email: String) : ScreenIntent()
    data class PasswordChanged(val password: String) : ScreenIntent()
    data object Register : ScreenIntent()
    data object Clear : ScreenIntent()
    data class SaveCharacter(val result: Result) : ScreenIntent()
    data object ReverseVisualPassword : ScreenIntent()
    data object GetEmail : ScreenIntent()
    data object GetPassword : ScreenIntent()
    data object DataClear : ScreenIntent()
}