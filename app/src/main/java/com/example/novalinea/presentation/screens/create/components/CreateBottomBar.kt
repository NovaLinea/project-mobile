package com.example.novalinea.presentation.screens.create.components

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.novalinea.common.Constants.BUTTON_CREATE_PROJECT
import com.example.novalinea.presentation.components.button_action.ButtonActionText

@Composable
fun CreateBottomBar(
	images: List<Uri>,
	enabledCreate: Boolean,
	onAddImage: (Uri) -> Unit,
	onDeleteImage: (Int) -> Unit,
	onCreate: () -> Unit
) {
	Column() {
		ImagesProject(
			images = images,
			onDeleteImage = { index ->
				onDeleteImage(index)
			}
		)

		Row(
			modifier = Modifier
				.padding(10.dp)
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			ChoiceImage(
				countImages = images.size,
				onAddImage = { uri ->
					onAddImage(uri)
				}
			)

			ButtonActionText(
				title = BUTTON_CREATE_PROJECT,
				enabled = enabledCreate
			) {
				onCreate()
			}
		}
	}
}