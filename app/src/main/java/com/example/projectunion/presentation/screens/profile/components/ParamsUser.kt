package com.example.projectunion.presentation.screens.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectunion.common.Constants

@Composable
fun ParamsUser(
	countProjects: Int,
	follows: Int,
	followings: Int
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 30.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceAround
	) {
		countProjects?.let { count ->
			ParamsUserItem(
				count = count,
				title = Constants.COUNT_PROJECTS,
				onClick = {}
			)
		}

		follows?.let { count ->
			ParamsUserItem(
				count = count,
				title = Constants.COUNT_FOLLOWS,
				onClick = {}
			)
		}

		followings?.let { count ->
			ParamsUserItem(
				count = count,
				title = Constants.COUNT_FOLLOWINGS,
				onClick = {}
			)
		}
	}
}

@Composable
fun ParamsUserItem(
	count: Int,
	title: String,
	onClick: () -> Unit
) {
	Column(
		modifier = Modifier
			.clickable { onClick() },
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = "$count",
			style = MaterialTheme.typography.subtitle1
		)
		Text(
			text = title,
			style = MaterialTheme.typography.caption
		)
	}
}