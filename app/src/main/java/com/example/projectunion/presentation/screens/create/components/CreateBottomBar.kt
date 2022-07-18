package com.example.projectunion.presentation.screens.create.components

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectunion.common.Constants.BUTTON_CREATE_PROJECT
import com.example.projectunion.presentation.components.button_action.ButtonActionText

@Composable
fun CreateBottomBar(
	images: MutableList<Uri>,
	enabledCreate: Boolean,
	onClickCreate: () -> Unit
) {
	Column() {
		ImagesProject(images)
		Row(
			modifier = Modifier
				.padding(10.dp)
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			ChoiceImage(images)

			ButtonActionText(
				title = BUTTON_CREATE_PROJECT,
				enabled = enabledCreate
			) {
				onClickCreate()
			}
		}
	}
}