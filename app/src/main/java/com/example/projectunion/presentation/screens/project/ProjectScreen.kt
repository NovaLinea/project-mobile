package com.example.projectunion.presentation.screens.project

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.projectunion.common.Constants.ARGUMENT_PROFILE_KEY
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.loader.Loader
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.navigation.nav_graph.BottomNavGraph
import com.example.projectunion.presentation.screens.project.components.ProjectBottomBar
import com.example.projectunion.presentation.screens.project.components.ProjectInformation
import com.example.projectunion.presentation.screens.project.components.ProjectTopBar

@Composable
fun ProjectScreen(
	navController: NavHostController,
	viewModel: ProjectViewModel = hiltViewModel()
) {
	val state = viewModel.state.observeAsState(Response.Success(null)).value

	Scaffold(
		topBar = { ProjectTopBar(navController) },
		bottomBar = {
			ProjectBottomBar(
				projectPrice = "15000"
			) {

			}
		}
	) {
		innerPadding ->
			Box(
				modifier = Modifier.padding(innerPadding)
			) {
				when(state) {
					is Response.Loading -> Loader()
					is Response.Success -> {
						if (state.data != null) {
							ProjectInformation(
								project = state.data
							) {
								navController.navigate(
									MainNavRoute.Profile.route + "?${ARGUMENT_PROFILE_KEY}=${state.data.creatorID}"
								)
							}
						}
					}
					is Response.Error -> Log.d(TAG, state.message)
				}
			}
	}
}