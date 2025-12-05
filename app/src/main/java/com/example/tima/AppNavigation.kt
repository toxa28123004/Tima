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
import com.example.tima.Screens.ChangePassword
import com.example.tima.Screens.CharacterScreen
import com.example.tima.Screens.HomeScreen
import com.example.tima.Screens.LoginScreen
import com.example.tima.Screens.ProfileScreen
import com.example.tima.Screens.SettingsScreen


@Composable
fun AppNavigation() {
    val context = LocalContext.current
    val activity = LocalActivity.current
    val focusManager = LocalFocusManager.current
    val viewModel: MyViewModel = viewModel(factory = MyViewModelFactory(context))
    val state by viewModel.state.collectAsState(initial = ScreenState())
    val navController = rememberNavController()
    val startDestination =
        remember(state.isRegistered) { if (state.isRegistered) Routes.LOGIN else Routes.CHARACTER }
    val icon = if (state.isVisualPassword) {
        R.drawable.ic_open_eye
    } else {
        R.drawable.ic_closed_eye
    }
    val clickBack: () -> Unit = { navController.navigateUp() }



    LaunchedEffect(state.isNavigateToHome) {
        navController.navigate(Routes.CHARACTER) { popUpTo(0) }
    }



    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.LOGIN) {
            LoginScreen(
                visibleEmail = state.visibleEmail,
                visiblePassword = state.visiblePassword,
                focusManager = focusManager,
                password = state.password,
                email = state.email,
                emailChanged = { email ->
                    viewModel.onIntent(ScreenIntent.EmailSave(email))
                },
                passwordChanged = {
                    viewModel.onIntent(ScreenIntent.PasswordSave(it))
                },
                register = {
                    viewModel.onIntent(ScreenIntent.Register)
                },
            )
        }
        composable(Routes.HOME) {
            HomeScreen(
                clickBack = clickBack,
            )
        }
        composable(Routes.CHARACTER) {
            CharacterScreen(
                characters = state.characters,
                isLoading = state.isLoading,
                onClick = {
                    navController.navigate(Routes.PROFILE)
                    viewModel.onIntent(ScreenIntent.SaveCharacter(it))
                },
                navigate = { navController.navigate(Routes.SETTINGS) },
                getData = {
                    viewModel.onIntent(ScreenIntent.GetEmail)
                    viewModel.onIntent(ScreenIntent.GetPassword)
                },
                navigateHome = { navController.navigate(Routes.HOME) },
                loadCharacters = { viewModel.onIntent(ScreenIntent.LoadCharacters) }
            )
        }
        composable(Routes.PROFILE) {
            ProfileScreen(
                result = state.person,
                clickBack = clickBack,

                )
        }
        composable(Routes.SETTINGS) {
            SettingsScreen(
                icon = icon,
                onClick = { viewModel.onIntent(ScreenIntent.ReverseVisualPassword) },
                password = state.password,
                email = state.email,
                isPasswordVisible = state.isVisualPassword,
                exit = { activity?.finish() },
                exitInProfile = {
                    viewModel.onIntent(ScreenIntent.Clear)
                    viewModel.onIntent(ScreenIntent.DataClear)
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0)
                    }
                },
                clickBack = clickBack,
                navigateToChangePassword = {
                    navController.navigate(Routes.CHANGE_PASSWORD)
                }

            )
        }
        composable(Routes.CHANGE_PASSWORD) {
            ChangePassword(
                passwordChanged = {
                    viewModel.onIntent(ScreenIntent.ChangePassword(it))
                },
                passwordChanged1 = {
                    viewModel.onIntent(ScreenIntent.ChangePassword1(it))
                },
                newPassword = state.newPassword,
                saveNewPassword = {
                    if (state.password == state.oldPassword) {
                        println("### ktc")
                        if (state.newPassword == state.newPassword1 && state.newPassword1.isNotEmpty()) {
                            viewModel.onIntent(ScreenIntent.SaveNewPassword)
                            clickBack()
                        } else {
                            println("### hui")
                            viewModel.onIntent(ScreenIntent.CheckEquallyNewPasswords)
                        }
                    } else {
                        viewModel.onIntent(ScreenIntent.CheckEmptyOldPassword)
                    }

                },
                newPassword1 = state.newPassword1,
                checkPassword = {
                    viewModel.onIntent(ScreenIntent.CheckPassword(it))
                },
                oldPassword = state.oldPassword,
                visiblePassword = state.visiblePassword,
                visibleNewPassword = state.visibleNewPassword,
                clickBack = clickBack,
            )
        }
    }
}