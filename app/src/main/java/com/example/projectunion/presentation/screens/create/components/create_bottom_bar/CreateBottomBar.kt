package com.example.projectunion.presentation.screens.create.components.create_bottom_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectunion.common.Constants
import com.example.projectunion.presentation.components.button_action.ButtonAction

@Composable
fun CreateBottomBar(
	enabledCreate: Boolean,
	onClickCreate: () -> Unit
) {
	Row(
		modifier = Modifier
			.padding(10.dp)
			.fillMaxWidth(),
		horizontalArrangement = Arrangement.End
	) {
		ButtonAction(
			title = Constants.CREATE_PROJECT,
			enabled = enabledCreate
		) {
			onClickCreate()
		}
	}
}