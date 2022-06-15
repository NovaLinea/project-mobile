package com.example.projectunion.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectunion.R
import com.example.projectunion.common.Constants
import com.example.projectunion.domain.model.Project
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.navigation.Router
import com.example.projectunion.presentation.screens.home.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
	externalRouter: Router,
	viewModel: HomeViewModel = hiltViewModel()
) {
	val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
	val currentDate = sdf.format(Date())
	val projects = listOf(
		Project(
			id = "0j1ew93kdgYJSZ9fXa4YSXrM6pn1",
			title = "Title",
			description = "Description",
			price = 15000,
			createdAt = currentDate,
			creatorName = "Vanya Palamarenko"
		)
	)

	Scaffold(
		modifier = Modifier.fillMaxSize(),
		topBar = { TopBar(externalRouter) },
		floatingActionButton = { FloatingButton(externalRouter, viewModel.isAuth) }
	) {
		LazyColumn(
			modifier = Modifier
				.background(colorResource(id = R.color.app_background))
				.fillMaxSize()
		) {
			projects.map { item {
				ProjectItem(it, externalRouter)
			}}
		}
	}
}

@Composable
fun TopBar(
	externalRouter: Router
) {
	TopAppBar(
		modifier = Modifier
			.fillMaxWidth(),
		title = {
			Box(
				modifier = Modifier
					.fillMaxWidth(),
				Alignment.Center
			) {
				Text(
					text = stringResource(R.string.home_screen),
					style = TextStyle(
						fontWeight = FontWeight.W600,
						fontSize = 18.sp
					)
				)
			}
		},
		/*navigationIcon = {
			IconButton(
				onClick = {
					if (isAuth)
						externalRouter.navigateTo(MainNavRoute.Profile.route)
					else
						externalRouter.navigateTo(AUTHENTICATION_ROUTE)
				}
			) {
				Icon(
					imageVector = Icons.Default.Person,
					contentDescription = "Person icon",
					tint = Color.Black
				)
			}
		},
		actions = {
			IconButton(
				onClick = {
					externalRouter.navigateTo(MainNavRoute.Notifications.route)
				}
			) {
				Icon(
					imageVector = Icons.Default.Notifications,
					contentDescription = "Notifications icon",
					tint = Color.Black
				)
			}
			IconButton(
				onClick = {
					externalRouter.navigateTo(MainNavRoute.Search.route)
				}
			) {
				Icon(
					imageVector = Icons.Default.Search,
					contentDescription = "Search icon",
					tint = Color.Black
				)
			}
		},*/
		backgroundColor = Color.White,
		elevation = 1.dp
	)
}

@Composable
fun FloatingButton(
	externalRouter: Router,
	isAuth: Boolean
) {
	FloatingActionButton(
		onClick = {
			if (!isAuth)
				externalRouter.navigateTo(Constants.AUTHENTICATION_ROUTE)
			else
				externalRouter.navigateTo(MainNavRoute.Create.route)
		},
		backgroundColor = colorResource(id = R.color.app_blue),
		contentColor = Color.White
	){
		Icon(imageVector = Icons.Default.Create, contentDescription = "Create project icon")
	}
}

@Composable
fun ProjectItem(
	project: Project,
	externalRouter: Router
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 0.dp, vertical = 7.dp)
			.clickable {
				externalRouter.navigateTo("project_screen/${project.id}")
			},
		backgroundColor = Color.White,
		elevation = 0.dp
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 10.dp, vertical = 5.dp),
		) {
			Row(
				modifier = Modifier
					.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = project.creatorName,
					style = TextStyle(
						color = Color.Gray,
						fontSize = 14.sp
					)
				)
				Text(
					text = project.createdAt,
					style = TextStyle(
						color = Color.Gray,
						fontSize = 14.sp
					)
				)
			}
			Row(
				modifier = Modifier
					.padding(top = 10.dp)
					.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = project.title,
					style = TextStyle(
						color = Color.Black,
						fontWeight = FontWeight.Medium,
						fontSize = 20.sp
					)
				)
				Text(
					text = "${project.price}₽",
					style = TextStyle(
						color = Color.Black,
						fontWeight = FontWeight.Medium,
						fontSize = 20.sp
					)
				)
			}
			Text(
				text = project.description,
				modifier = Modifier
					.padding(top = 5.dp),
				style = TextStyle(
					color = Color.Black,
					fontSize = 15.sp
				)
			)
		}
	}
}