package com.example.novalinea.presentation.screens.project.components.bottom_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.novalinea.common.Constants.BUTTON_JOIN_TEAM_PROJECT
import com.example.novalinea.presentation.components.button_action.ButtonActionText

@Composable
fun ProjectTeamBottomBar(
	showBottomSheet: () -> Unit
) {
	BottomNavigation(
		backgroundColor = Color.White,
		elevation = 0.dp
	) {
		Row(
			modifier = Modifier
				.padding(vertical = 2.dp, horizontal = 15.dp)
				.fillMaxWidth()
				.background(Color.White),
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically
		) {
			ButtonActionText(
				title = BUTTON_JOIN_TEAM_PROJECT,
				enabled = true,
				isMaxWidth = true
			) {
				showBottomSheet()
			}
		}
	}
}