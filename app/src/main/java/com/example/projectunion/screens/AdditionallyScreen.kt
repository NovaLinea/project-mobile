package com.example.projectunion.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.R
import com.example.projectunion.navigation.ProjectNavRoute
import com.example.projectunion.navigation.Router

@Composable
fun AdditionallyScreen(externalRouter: Router) {
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
			AdditionallyItem(title = "Профиль", icon = R.drawable.ic_person, ProjectNavRoute.Profile.route, externalRouter)
			AdditionallyItem(title = "Избранное", icon = R.drawable.ic_favoritres, ProjectNavRoute.Favorites.route, externalRouter)
			AdditionallyItem(title = "Настройки", icon = R.drawable.ic_settings, ProjectNavRoute.Settings.route, externalRouter)
		}
	}
}

@Composable
fun AdditionallyItem(title: String, icon: Int, route: String, externalRouter: Router) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 10.dp, vertical = 5.dp)
			.clickable {
				externalRouter.navigateTo(route)
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
				Image(
					painter = painterResource(id = icon),
					contentDescription = "image",
					contentScale = ContentScale.Crop,
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