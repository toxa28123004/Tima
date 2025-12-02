package com.example.tima


data class ScreenState(
	val email: String = "",
	val password: String = "",
	val visibleEmail: Boolean = false,
	val visiblePassword: Boolean = false,
	val isNavigateToHome: Boolean = false,
	val isRegistered: Boolean = false
)
