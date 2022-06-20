package com.example.projectunion.presentation.screens.create.components.create_bottom_bar

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectunion.common.Constants
import com.example.projectunion.presentation.components.button_action.ButtonAction
import com.example.projectunion.presentation.screens.create.components.choice_image.ChoiceImage
import com.example.projectunion.presentation.screens.create.components.images_project.ImagesProject

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

			ButtonAction(
				title = Constants.CREATE_PROJECT,
				enabled = enabledCreate
			) {
				onClickCreate()
			}
		}
	}
}