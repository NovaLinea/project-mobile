package com.example.projectunion.presentation.screens.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projectunion.R
import com.example.projectunion.common.Constants
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.domain.model.ProjectTape
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.loader.Loader
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.screens.home.components.ProjectItem
import com.example.projectunion.presentation.screens.profile.components.ProfileInformation
import com.example.projectunion.presentation.screens.profile.components.ProfileTopBar

@Composable
fun ProfileScreen(
	navController: NavController,
	viewModel: ProfileViewModel = hiltViewModel()
) {
	val stateProfile = viewModel.stateProfile.observeAsState(Response.Success(null)).value
	val stateProjects = viewModel.stateProjects.observeAsState(Response.Success(emptyList())).value
	var countProjects = 0

	if (stateProjects is Response.Success)
		countProjects = stateProjects.data.size

	Scaffold(
		topBar = { ProfileTopBar(navController) },
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				//.verticalScroll(rememberScrollState())
		) {
			when(stateProfile) {
				is Response.Loading -> Loader()
				is Response.Success -> {
					stateProfile.data?.let { user ->
						ProfileInformation(
							user = user,
							countProjects = countProjects,
							navController = navController
						)
					}
				}
				is Response.Error -> Log.d(TAG, stateProfile.message)
			}

			Spacer(modifier = Modifier.height(20.dp))
			
			when(stateProjects) {
				is Response.Loading -> Loader()
				is Response.Success -> {
					LazyColumn(
						modifier = Modifier
							.background(colorResource(id = R.color.app_background))
							.fillMaxSize()
					) {
						items(stateProjects.data) { project ->
							ProjectItem(
								project = project,
								openProfile = { creatorID ->
									openProfile(
										navController = navController,
										id = creatorID
									)
								},
								openProject = {
									openProject(
										navController = navController,
										project = project
									)
								}
							)
						}
					}
				}
				is Response.Error -> Log.d(TAG, stateProjects.message)
			}
		}
	}
}

fun changePhoto() {

}

fun openProject(
	navController: NavController,
	project: ProjectTape
) {
	navController.navigate(
		MainNavRoute.Project.route
				+ "?${Constants.ARGUMENT_PROJECT_ID_KEY}=${project.id}"
				+ "&${Constants.ARGUMENT_PROJECT_PRICE_KEY}=${project.price}"
	)
}

fun openProfile(
	navController: NavController,
	id: String
) {
	navController.navigate(
		MainNavRoute.Profile.route + "?${Constants.ARGUMENT_PROFILE_KEY}=${id}"
	)
}