package com.example.projectunion.presentation.screens.project

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projectunion.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.projectunion.common.Constants.AUTHENTICATION_ROUTE
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.common.Constants.USER
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.loader.Loader
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.screens.project.components.ProjectBottomBar
import com.example.projectunion.presentation.screens.project.components.ProjectInformation
import com.example.projectunion.presentation.screens.project.components.ProjectTopBar

@Composable
fun ProjectScreen(
	price: Int,
	navController: NavController,
	viewModel: ProjectViewModel = hiltViewModel()
) {
	val stateProject = viewModel.stateProject.observeAsState(Response.Success(null)).value

	when(val stateSend = viewModel.stateSend.observeAsState(Response.Success(false)).value) {
		is Response.Loading -> Log.d(TAG, "Loading")
		is Response.Error -> Log.d(TAG, stateSend.message)
		is Response.Success -> {
			if (stateSend.data)
				Log.d(TAG, "Success send message about buy project")
		}
	}

	Scaffold(
		topBar = { ProjectTopBar(navController) },
		bottomBar = {
			ProjectBottomBar(
				projectPrice = "$price",
				onClickBuy = {
					if (USER.id != null)
						viewModel.sendMessage()
					else
						navController.navigate(AUTHENTICATION_ROUTE)
				}
			)
		}
	) {
		innerPadding ->
			Box(
				modifier = Modifier.padding(innerPadding)
			) {
				when(stateProject) {
					is Response.Loading -> Loader()
					is Response.Success -> {
						if (stateProject.data != null) {
							ProjectInformation(
								project = stateProject.data
							) {
								navController.navigate(
									MainNavRoute.Profile.route
											+ "?${ARGUMENT_USER_ID_KEY}=${stateProject.data.creatorID.toString()}"
								)
							}
						}
					}
					is Response.Error -> Log.d(TAG, stateProject.message)
				}
			}
	}
}