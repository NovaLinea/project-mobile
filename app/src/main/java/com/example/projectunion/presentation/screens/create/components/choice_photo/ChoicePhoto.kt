package com.example.projectunion.presentation.screens.create.components.choice_photo

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ChoicePhoto(
	imageUri: Uri? = null
) {
	var imageUri by remember { mutableStateOf<Uri?>(null) }
	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.GetContent()
	) { uri: Uri? ->
		imageUri = uri
	}

	Card(
		modifier = Modifier
			.height(70.dp)
			.width(100.dp),
		backgroundColor = Color.White,
		elevation = 5.dp,
		shape = RoundedCornerShape(10.dp)
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.clickable {
					launcher.launch("image/*")
				},
			contentAlignment = Alignment.Center
		) {
			if (imageUri != null) {
				Image(
					painter = rememberImagePainter(data = imageUri),
					contentDescription = null,
					modifier = Modifier.fillMaxSize()
				)
			}
			else {
				Icon(
					imageVector = Icons.Default.Add,
					contentDescription = "App photo icon",
					modifier = Modifier
						.padding(15.dp),
					tint = Color.DarkGray
				)
			}
		}
	}
}