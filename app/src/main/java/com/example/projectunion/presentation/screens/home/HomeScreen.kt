package com.example.projectunion.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectunion.R
import com.example.projectunion.common.Constants
import com.example.projectunion.common.Constants.MAIN_SCREEN
import com.example.projectunion.domain.model.Project
import com.example.projectunion.presentation.components.floating_button.FloatingButton
import com.example.projectunion.presentation.navigation.Router
import com.example.projectunion.presentation.screens.home.components.ProjectItem
import com.example.projectunion.presentation.components.top_bar.TopBar
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
	externalRouter: Router,
	onClickCreate: () -> Unit,
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
		topBar = { TopBar(MAIN_SCREEN) },
		floatingActionButton = {
			FloatingButton(
				imageVector = Icons.Default.Create,
				onClick = {
					if (!viewModel.isAuth)
						externalRouter.navigateTo(Constants.AUTHENTICATION_ROUTE)
					else
						onClickCreate()
				}
			)
		}
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