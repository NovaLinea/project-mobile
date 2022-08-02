package com.example.novalinea.presentation.screens.project.components.bottom_sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.novalinea.common.Constants.BUTTON_SEND_APPLICATION
import com.example.novalinea.common.Constants.TEXT_BUY_PROJECT
import com.example.novalinea.common.Constants.TITLE_BUY_PROJECT
import com.example.novalinea.presentation.components.button_action.ButtonActionText

@Composable
fun BuyProject(
	onSendApplication: () -> Unit,
	hideBottomSheet: () -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.White)
	) {
		Row(
			modifier = Modifier
				.padding(top = 10.dp)
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.Center
		) {
			Card(
				modifier = Modifier
					.height(5.dp)
					.width(50.dp),
				backgroundColor = Color.LightGray,
				elevation = 0.dp,
				shape = RoundedCornerShape(10.dp)
			) {}
		}
		Spacer(modifier = Modifier.height(15.dp))

		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 20.dp)
		) {
			Text(
				text = TITLE_BUY_PROJECT,
				style = MaterialTheme.typography.h6
			)

			Spacer(modifier = Modifier.height(10.dp))
			Text(
				text = TEXT_BUY_PROJECT,
				style = MaterialTheme.typography.body2
			)

			Spacer(modifier = Modifier.height(15.dp))
			ButtonActionText(
				title = BUTTON_SEND_APPLICATION,
				enabled = true,
				isMaxWidth = true
			) {
				onSendApplication()
				hideBottomSheet()
			}
		}

		Spacer(modifier = Modifier.height(15.dp))
	}
}