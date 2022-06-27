package com.example.projectunion.presentation.screens.edit_profile.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projectunion.common.Constants.EDIT_PROFILE_SCREEN
import com.example.projectunion.presentation.components.icon_button.IconButtonAction

@Composable
fun EditProfileTopBar(
	navController: NavController,
	onClickSave: () -> Unit
) {
	TopAppBar(
		title = {
			Text(
				text = EDIT_PROFILE_SCREEN,
				style = MaterialTheme.typography.h6
			)
		},
		navigationIcon = {
			IconButtonAction(Icons.Default.ArrowBack) {
				navController.popBackStack()
			}
		},
		actions = {
			IconButtonAction(Icons.Default.Check) {
				onClickSave()
			}
		},
		backgroundColor = Color.White,
		elevation = 0.dp
	)
}