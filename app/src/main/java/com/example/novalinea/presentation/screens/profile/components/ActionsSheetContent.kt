package com.example.novalinea.presentation.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.common.Constants
import com.example.novalinea.common.Constants.BUTTON_FAVORITES
import com.example.novalinea.common.Constants.BUTTON_LOGOUT
import com.example.novalinea.common.Constants.BUTTON_SETTINGS

@Composable
fun ActionsSheetContent() {
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
		Spacer(modifier = Modifier.height(10.dp))

		ActionItem(
			title = BUTTON_SETTINGS,
			icon = Icons.Default.Settings,
			onClick = {}
		)

		ActionItem(
			title = BUTTON_FAVORITES,
			icon = Icons.Default.Bookmark,
			onClick = {}
		)

		ActionItem(
			title = BUTTON_LOGOUT,
			icon = Icons.Default.Logout,
			onClick = {}
		)

		Spacer(modifier = Modifier.height(15.dp))
	}
}

@Composable
fun ActionItem(
	title: String,
	icon: ImageVector,
	onClick: () -> Unit
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(45.dp)
			.background(Color.White)
			.clickable { onClick() },
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Start
	) {
		Box(
			modifier = Modifier
				.padding(start = 20.dp)
				.size(20.dp),
			contentAlignment = Alignment.Center
		) {
			Icon(
				modifier = Modifier.fillMaxSize(),
				imageVector = icon,
				contentDescription = null
			)
		}

		Text(
			text = title,
			modifier = Modifier.padding(start = 15.dp),
			style = TextStyle(
				fontSize = 17.sp,
				fontWeight = FontWeight.W500,
				color = Color.Black,
			),
		)
	}
}