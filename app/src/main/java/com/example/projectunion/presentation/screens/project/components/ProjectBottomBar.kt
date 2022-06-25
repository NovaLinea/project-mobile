package com.example.projectunion.presentation.screens.project.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projectunion.common.Constants.BUY_PROJECT
import com.example.projectunion.presentation.components.button_action.ButtonActionText

@Composable
fun ProjectBottomBar(
	projectPrice: String,
	onClickBuy: () -> Unit
) {
	BottomNavigation(
		backgroundColor = Color.White,
		elevation = 7.dp
	) {
		Row(
			modifier = Modifier
				.padding(vertical = 5.dp, horizontal = 10.dp)
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = projectPrice + "₽",
				style = MaterialTheme.typography.h6
			)

			ButtonActionText(
				title = BUY_PROJECT,
				enabled = true
			) {
				onClickBuy()
			}
		}
	}
}