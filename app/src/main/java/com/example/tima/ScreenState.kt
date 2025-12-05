package com.example.tima

import com.example.tima.models.Result


data class ScreenState(
    val email: String = "",
    val password: String = "",
    val visibleEmail: Boolean = false,
    val visiblePassword: Boolean = false,
    val isNavigateToHome: Boolean = false,
    val isRegistered: Boolean = false,
    val characters: List<Result> = emptyList(),
    val person: Result? = null,
    val isLoading: Boolean = false,
    val isVisualPassword: Boolean = false,
    val savePassword: String = "",
    val currentPage1: Int = 1,
    val currentPage: Int = 2,
    val hasMorePages: Boolean = true,
    val newPassword: String = "",
    val newPassword1: String = "",
    val oldPassword: String = "",
    val visibleNewPassword: Boolean = false,


    )