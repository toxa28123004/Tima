package com.example.tima

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
	val context = LocalContext.current
	val viewModel: MyViewModel = viewModel(factory = MyViewModelFactory(context))
	val state by viewModel.state.collectAsState()


	RegisterContent(
		email = state.email,
		password = state.password,
		viewModel = viewModel,
		navController = navController
	)
}

@Composable
fun RegisterContent(
	email: String,
	password: String,
	viewModel: MyViewModel,
	navController: NavController
) {
	var visibleEmail by remember { mutableStateOf(false) }
	var visiblePassword by remember { mutableStateOf(false) }
	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text("авторизация")
		Spacer(modifier = Modifier.height(24.dp))
		TextField(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			shape = RoundedCornerShape((16.dp)),
			value = email,
			onValueChange = { email ->
				viewModel.onIntent(LoginIntent.EmailChanged(email))
				if (email.isNotEmpty()){
					visibleEmail = false
				}
			},
			label = { Text("введите Email") },
			colors = TextFieldDefaults.colors(
				focusedIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent
			)
		)
		AnimatedVisibility(
			visible = visibleEmail,
			enter = fadeIn(),
			exit = fadeOut()
		) {
			Text("заполните Email!!!")
		}
		Spacer(modifier = Modifier.height(24.dp))
		TextField(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			shape = RoundedCornerShape((16.dp)),
			value = password,
			onValueChange = { password ->
				viewModel.onIntent(LoginIntent.PasswordChanged(password))
				if (password.isNotEmpty()){
					visiblePassword = false
				}
			},
			label = { Text("введите пароль") },
			colors = TextFieldDefaults.colors(
				focusedIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent
			)
		)
		AnimatedVisibility(
			visible = visiblePassword,
			enter = fadeIn(),
			exit = fadeOut()
		) {
			Text("ПАРОЛЬ!!!")
		}

		Spacer(modifier = Modifier.height(24.dp))
		Button(onClick = {
			viewModel.onIntent(LoginIntent.Register)
			if (email.isNotEmpty() && password.isNotEmpty()) {
				navController.navigate("home"){
					popUpTo(0)
				}
			}
			if (email.isEmpty()){
				visibleEmail = true
			}
			if (password.isEmpty()){
				visiblePassword = true
			}

		}) {
			Text("зарегистрироваться")
		}
	}
}
