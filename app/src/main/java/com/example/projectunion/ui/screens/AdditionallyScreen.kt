package com.example.projectunion.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectunion.R
import com.example.projectunion.navigation.ProjectNavRoute
import com.example.projectunion.navigation.Router

@Composable
fun AdditionallyScreen(navController: NavController) {
	Scaffold(
		topBar = {
			TopAppBar(
				backgroundColor = Color.White,
				elevation = 0.dp
			) {
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.Center
				) {
					Text(
						text = stringResource(id = R.string.additionally_screen),
						style = TextStyle(
							color = Color.Black,
							fontWeight = FontWeight.W600,
							fontSize = 18.sp
						)
					)
				}
			}
		},
	) {
		Column {
			AdditionallyItem(title = "Профиль", icon = Icons.Default.Person, ProjectNavRoute.Profile.route, navController)
			AdditionallyItem(title = "Избранное", icon = Icons.Default.Bookmark, ProjectNavRoute.Favorites.route, navController)
			AdditionallyItem(title = "Настройки", icon = Icons.Default.Settings, ProjectNavRoute.Settings.route, navController)
		}
	}
}

@Composable
fun AdditionallyItem(title: String, icon: ImageVector, route: String, navController: NavController) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 10.dp, vertical = 5.dp)
			.clickable {
				navController.navigate(route)
			},
		shape = RoundedCornerShape(10.dp),
		elevation = 0.dp
	) {
		Box(
			modifier = Modifier
				.background(colorResource(id = R.color.app_background))
				.fillMaxWidth()
				.padding(10.dp),
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically
			) {
				Icon(
					imageVector = icon,
					contentDescription = "Icon",
					modifier = Modifier
					.padding(5.dp)
					.size(24.dp)
					.clip(CircleShape)
				)
				Box(
					modifier = Modifier
						.padding(start = 10.dp)
				) {
					Text(
						text = title,
						style = TextStyle(
							color = Color.Black,
							fontWeight = FontWeight.Bold,
							fontSize = 17.sp
						)
					)
				}
			}
		}
	}
}