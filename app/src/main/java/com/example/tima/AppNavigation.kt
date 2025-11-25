package com.example.tima

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


@Composable
fun AppNavigation() {
	val context = LocalContext.current
	val viewModel: MyViewModel = viewModel(factory = MyViewModelFactory(context))
	val state by viewModel.state.collectAsState(initial = ScreenState())
	val navController = rememberNavController()
	val preferenceManager = PreferenceManager(context)
	val startDestination = if (preferenceManager.getPassword().isNullOrEmpty()) {
		"login"
	} else {
		"home"
	}

	NavHost(navController = navController, startDestination = startDestination) {
		composable("login") {
			LoginScreen(
				navController = navController,
				viewModel = viewModel,
				state = state
			)
		}
		composable("home") {
			HomeScreen(
				navController = navController,
				viewModel = viewModel
			)
		}
	}
}