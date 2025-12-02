package com.example.tima

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
	onClick: () -> Unit,
	onClear:() -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.SpaceBetween
	) {
		Spacer(modifier = Modifier.height(24.dp))
		Image(
			painter = painterResource(id = R.drawable.my_image_monkey),
			contentDescription = "Example",
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp)
				.clip(RoundedCornerShape(16.dp)),
			contentScale = ContentScale.Crop
		)
		Column(
			modifier = Modifier
				.fillMaxWidth()
		) {
			ElevatedButton(onClick = { onClick() }) {
				Text("выйти")

			}
			Spacer(modifier = Modifier.height(24.dp))
			ElevatedButton(onClick = {
				onClear()
			}) {
				Text("выйти из аккаунта")

			}
		}
		Spacer(modifier = Modifier.height(24.dp))
	}
}