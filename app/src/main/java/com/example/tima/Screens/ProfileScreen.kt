package com.example.tima.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.tima.models.Result

@Composable
fun ProfileScreen(result: Result?) {
    if (result == null) return
    Column {
        AsyncImage(
            model = result.image,
            contentDescription = "Character image",
            modifier = Modifier
        )
        Text(result.name)
        Text(result.status.toString())
        Text(result.species.toString())
        Text(result.type)
        Text(result.gender.toString())
        Text(result.origin.toString())
        Text(result.location.toString())

    }
}