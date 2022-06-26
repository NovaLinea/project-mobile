package com.example.projectunion.presentation.screens.create.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun ChoiceImage(images: MutableList<Uri>) {
	val maxImagesProject = 3
	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.GetContent()
	) { uri: Uri? ->
		uri?.let { images.add(images.size, it) }
	}

	IconButton(
		onClick = {
			if (images.size < maxImagesProject)
				launcher.launch("image/*")
		}
	) {
		Icon(
			imageVector = Icons.Default.PhotoLibrary,
			contentDescription = null,
			tint = Color.DarkGray
		)
	}
}