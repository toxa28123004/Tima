package com.example.tima.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tima.R
import java.nio.file.WatchEvent

@Composable
fun ChangePassword(
    passwordChanged: (String) -> Unit,
    newPassword: String,
    saveNewPassword: () -> Unit,
    newPassword1: String,
    passwordChanged1: (String) -> Unit,
    checkPassword: (String) -> Unit,
    oldPassword: String,
    visiblePassword: Boolean,
    visibleNewPassword: Boolean,
    clickBack: () -> Unit
) {
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(
            Modifier
                .height(24.dp)
        )
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { clickBack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = ""
                )
            }
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = oldPassword,
            label = { Text("волшебное слово") },
            onValueChange = { password ->
                checkPassword(password)
            },
            shape = RoundedCornerShape(16.dp),
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
        Spacer(
            Modifier
                .height(24.dp)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = newPassword1,
            label = { Text("новое волшебное слово") },
            onValueChange = {
                passwordChanged1(it)
            },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(
            Modifier
                .height(24.dp)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = newPassword,
            label = { Text("повтори волшебное слово") },
            onValueChange = { newPassword ->
                passwordChanged(newPassword) },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        AnimatedVisibility(
            visible = visibleNewPassword,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text("заполни поля одинаково!")
        }
        Spacer(
            Modifier
                .height(24.dp)
        )
        Button(onClick = { saveNewPassword() }) {
            Text("изменииь волшебное слово")
        }
    }

}
