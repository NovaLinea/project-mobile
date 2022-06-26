package com.example.projectunion.presentation.screens.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projectunion.common.Constants
import com.example.projectunion.presentation.components.button_action.ButtonActionIcon
import com.example.projectunion.presentation.components.button_action.ButtonActionText

@Composable
fun ActionsUser(navController: NavController) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 20.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
	) {
		/*ButtonAction(
			title = EDIT_PROFILE,
			onClicked = {}
		)*/
		ButtonActionText(
			title = Constants.SUBSCRIBE_USER,
			onClicked = {}
		)
		Box(
			modifier = Modifier.padding(start = 5.dp)
		) {
			ButtonActionIcon(
				icon = Icons.Default.Message,
				onClicked = {}
			)
		}
	}
}