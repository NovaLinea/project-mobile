package com.example.novalinea.presentation.screens.create.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.novalinea.R
import com.example.novalinea.presentation.components.close_button.CloseButton

@Composable
fun CreateTopBar(
	stateSteps: MutableList<Boolean>,
	onClickBack: () -> Unit
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
			CloseButton {
				onClickBack()
			}
		}

		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			Row {
				stateSteps.forEachIndexed { index, step ->
					StepItem(
						isDone = step,
						isFirst = index == 0,
						isLast = index == stateSteps.lastIndex
					)
				}
			}
		}
	}
}

@Composable
fun StepItem(
	isDone: Boolean,
	isFirst: Boolean,
	isLast: Boolean
) {
	Card(
		modifier = Modifier
			.width(50.dp)
			.height(7.dp)
			.padding(horizontal = 2.dp),
		backgroundColor = if (isDone) colorResource(id = R.color.app_light_blue) else Color.LightGray,
		shape = if (isFirst) RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
				else if (isLast) RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
				else RoundedCornerShape(0.dp),
		elevation = 0.dp
	) {}
}