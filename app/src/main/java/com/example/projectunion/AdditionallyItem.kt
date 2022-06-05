package com.example.projectunion

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdditionallyItem(title: String, icon: Int) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 10.dp, vertical = 5.dp)
			.clickable {
				Log.d("AppLog", "Click $title")
			},
		shape = RoundedCornerShape(10.dp),
		elevation = 0.dp
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.padding(10.dp),
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically
			) {
				Image(
					painter = painterResource(id = icon),
					contentDescription = "image",
					contentScale = ContentScale.Crop,
					modifier = Modifier
						.padding(5.dp)
						.size(24.dp)
						.clip(CircleShape)
				)
				Box(
					modifier = Modifier
						.padding(start = 10.dp)
				) {
					Text(
						text = title,
						style = TextStyle(
							color = Color.Black,
							fontWeight = FontWeight.Bold,
							fontSize = 17.sp
						)
					)
				}
			}
		}
	}
}