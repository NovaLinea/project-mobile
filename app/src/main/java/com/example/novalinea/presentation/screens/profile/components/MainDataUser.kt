package com.example.novalinea.presentation.screens.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.novalinea.R

@Composable
fun MainDataUser(
	name: String?,
	verify: Boolean,
	description: String?
) {
	name?.let { name ->
		Spacer(modifier = Modifier.height(5.dp))
		Row(
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = name,
				style = MaterialTheme.typography.h6
			)

			if (verify) {
				Icon(
					modifier = Modifier
						.size(24.dp)
						.padding(start = 5.dp),
					painter = painterResource(id = R.drawable.ic_verify_account),
					contentDescription = null,
					tint = colorResource(id = R.color.app_blue_verify)
				)
			}
		}
	}

	if (description != null && description.isNotEmpty()) {
		Spacer(modifier = Modifier.height(5.dp))
		Text(
			text = description,
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 30.dp),
			textAlign = TextAlign.Center,
			style = MaterialTheme.typography.body2,
			color = Color.DarkGray
		)
	}
}