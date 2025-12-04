package com.example.tima.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tima.R
import com.example.tima.models.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    result: Result?,
    clickBack: () -> Unit
) {
    if (result == null) return
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("")
        }, navigationIcon = {
            IconButton(onClick = { clickBack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = ""
                )
            }
        })
    }) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
        ) {
            item {
                AsyncImage(
                    model = result.image,
                    contentDescription = "Character image",
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            item {
                CardResult("Name:", result.name)
            }
            item {
                CardResult("Status:", result.status.toString())
            }
            item {
                CardResult("Species:", result.species.toString())
            }
            item {
                CardResult("Type:", result.type)
            }
            item {
                CardResult("Gender:", result.gender.toString())
            }
            item {
                CardResult("Origin Location:", result.origin.toString())
            }
            item {
                CardResult("Location:", result.location.toString())
            }
            item {
                CardResult("Number of episodes:", result.episode.size.toString())
            }
        }
    }

}

@Composable
fun CardResult(
    label: String, value: String
) {
    Card(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF5B6C6E)
        )

    ) {
        Column(
            Modifier.padding(16.dp)
        ) {
            Text(label, color = Color.Cyan)
            Text(value)
        }
    }
}