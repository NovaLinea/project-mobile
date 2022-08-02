package com.example.novalinea.presentation.screens.project.components.bottom_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.novalinea.R
import com.example.novalinea.common.Constants.BUTTON_JOIN_TEAM_PROJECT
import com.example.novalinea.presentation.components.button_action.ButtonActionText

@Composable
fun ProjectTeamBottomBar(
	onClickTeam: () -> Unit
) {
	BottomNavigation(
		backgroundColor = Color.White,
		elevation = 0.dp
	) {
		Column(
			modifier = Modifier.background(Color.White)
		) {
			Spacer(
				modifier = Modifier
					.height(1.dp)
					.fillMaxWidth()
					.background(colorResource(id = R.color.app_background))
			)

			Row(
				modifier = Modifier
					.padding(vertical = 2.dp, horizontal = 15.dp)
					.fillMaxWidth(),
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				ButtonActionText(
					title = BUTTON_JOIN_TEAM_PROJECT,
					enabled = true,
					isMaxWidth = true
				) {
					onClickTeam()
				}
			}
		}
	}
}