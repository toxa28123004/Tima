package com.example.tima.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.example.tima.R


@Composable
fun SettingsScreen(
    icon: Int,
    onClick: () -> Unit,
    password: String,
    email: String,
    isPasswordVisible: Boolean,
    exit: () -> Unit,
    exitInProfile: () -> Unit,
    clickBack: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))
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
        Card(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF5B6C6E)),
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row {
                    Text("email:", color = Color.Cyan)
                    Text(text = email)
                }

            }
        }
        Card(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF5B6C6E))
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
                    .height(50.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row {
                    Text("password:", color = Color.Cyan)
                    Text(
                        text =
                            if (isPasswordVisible) password else " * ".repeat(
                                password.length
                            )

                    )
                }

                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = onClick) {
                        Icon(
                            painter = painterResource(icon),
                            contentDescription = "Toggle password visibility",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(24.dp))
        ElevatedButton(onClick = { exit() }) {
            Text("выйти")

        }
        Spacer(modifier = Modifier.height(24.dp))
        ElevatedButton(onClick = {
            exitInProfile()
        }) {
            Text("выйти из аккаунта")
        }
    }
}