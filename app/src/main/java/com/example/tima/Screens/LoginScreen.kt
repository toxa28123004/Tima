package com.example.tima.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    email: String,
    password: String,
    visibleEmail: Boolean,
    visiblePassword: Boolean,
    focusManager: FocusManager,
    emailChanged: (String) -> Unit,
    passwordChanged: (String) -> Unit,
    register: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                focusManager.clearFocus()
            },
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
                emailChanged(email)
            },
            label = { Text("введите имя") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        if (visibleEmail) {
            Text("заполни блять имя!!!")
        }
        Spacer(modifier = Modifier.height(24.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape((16.dp)),
            value = password,
            onValueChange = { password ->
                passwordChanged(password)
            },
            label = { Text("введите волшебное слово") },
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
            Text("волшебное слово!!!")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { register() }) {
            Text("зарегистрироваться")
        }
    }
}
