package com.example.projectunion.presentation.screens.edit_profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projectunion.common.Constants
import com.example.projectunion.common.Constants.EDIT_PROFILE_SCREEN
import com.example.projectunion.presentation.components.icon_button.IconButtonAction

@Composable
fun EditProfileTopBar(
	onClickBack: () -> Unit,
	onClickSave: () -> Unit
) {
	Box(
		modifier = Modifier
			.height(55.dp)
			.fillMaxWidth()
	) {
		Box(
			modifier = Modifier
				.padding(start = 5.dp)
				.fillMaxSize(),
			contentAlignment = Alignment.CenterStart
		) {
			IconButtonAction(Icons.Default.ArrowBack) {
				onClickBack()
			}
		}

		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			Text(
				text = EDIT_PROFILE_SCREEN,
				style = MaterialTheme.typography.h6
			)
		}

		Box(
			modifier = Modifier
				.padding(end = 5.dp)
				.fillMaxSize(),
			contentAlignment = Alignment.CenterEnd
		) {
			IconButtonAction(Icons.Default.Check) {
				onClickSave()
			}
		}
	}
}