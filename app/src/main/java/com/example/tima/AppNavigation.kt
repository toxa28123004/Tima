package com.example.tima

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun AppNavigation() {
	val context = LocalContext.current
	val preferenceManager = PreferenceManager.getInstance(context)
	val navController = rememberNavController()
	val startDestination = if (preferenceManager.getPassword().isNullOrEmpty()) {
		"login"
	} else {
		"home"
	}

	NavHost(navController = navController, startDestination = startDestination) {
		composable("login") {
			LoginScreen(navController)
		}
		composable("home") {
			HomeScreen(
				navController,
			)
		}
	}
}