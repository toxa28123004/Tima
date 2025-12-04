package com.example.tima.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tima.models.Result


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreen(
    characters: List<Result>,
    onClick: (Result) -> Unit,
    isLoading: Boolean,
    navigate: () -> Unit,
    getData: () -> Unit,
    navigateHome: () -> Unit,
    loadCharacters: () -> Unit

) {
    if (isLoading) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    Button(onClick = {
                        navigateHome()
                    }
                    ) {
                        Text("🦖")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navigate()
                        getData()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "настройки"
                        )
                    }
                })
        }) { paddingValues ->
            LazyColumn(contentPadding = paddingValues, modifier = Modifier) {
                itemsIndexed(characters) { index , character ->
                 CharacterItem(
                     character,
                     onClick = { onClick(character) }
                 )
                    if (index == characters.lastIndex){
                        loadCharacters()
                    }
                }
            }
        }
    }
}
@Composable
fun CharacterItem (
    character: Result,
    onClick:()-> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = {
                onClick()
            }),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF5B6C6E)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = "Character image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Text(character.name)
        }
    }
}