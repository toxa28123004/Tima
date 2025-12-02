package com.example.tima

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun AppNavigation() {
	val context = LocalContext.current
	val activity = LocalActivity.current
	val focusManager = LocalFocusManager.current
	val viewModel: MyViewModel = viewModel(factory = MyViewModelFactory(context))
	val state by viewModel.state.collectAsState(initial = ScreenState())
	val navController = rememberNavController()
	val startDestination = remember(state.isRegistered) { if (state.isRegistered) "login" else "home" }

	LaunchedEffect(state.isNavigateToHome) {
		navController.navigate("home") { popUpTo(0) }
	}

	NavHost(navController = navController, startDestination = startDestination) {
		composable("login") {
			LoginScreen(
				visibleEmail = state.visibleEmail,
				visiblePassword = state.visiblePassword,
				focusManager = focusManager,
				password = state.password,
				email = state.email,
				emailChanged = { email ->
					viewModel.onIntent(LoginIntent.EmailChanged(email))
				},
				passwordChanged = {
					viewModel.onIntent(LoginIntent.PasswordChanged(it))
				},
				register = {
					viewModel.onIntent(LoginIntent.Register)
				},
			)
		}
		composable("home") {
			HomeScreen(
				onClick = { activity?.finish() },
				onClear = {
					viewModel.onIntent(LoginIntent.Clear)
					navController.navigate("login") {
						popUpTo(0)
					}
				},
			)
		}
	}
}