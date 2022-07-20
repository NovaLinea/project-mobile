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
import com.example.projectunion.common.Constants.MAX_IMAGES_PROJECT

@Composable
fun ChoiceImage(
	countImages: Int,
	onAddImage: (Uri) -> Unit,
) {
	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.GetContent()
	) { uri: Uri? ->
		uri?.let { onAddImage(it) }
	}

	IconButton(
		onClick = {
			if (countImages < MAX_IMAGES_PROJECT)
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