package com.example.projectunion.presentation.screens.project

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.projectunion.common.Constants
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.loader.Loader
import com.example.projectunion.presentation.screens.project.components.ProjectInformation
import com.example.projectunion.presentation.screens.project.components.ProjectTopBar

@Composable
fun ProjectScreen(
	navController: NavHostController,
	viewModel: ProjectViewModel = hiltViewModel()
) {
	val state = viewModel.state.observeAsState(Response.Success(null)).value

	Scaffold(
		topBar = { ProjectTopBar(navController) }
	) {
		when(state) {
			is Response.Loading -> Loader()
			is Response.Success -> {
				if (state.data != null) {
					ProjectInformation(project = state.data)
				}
			}
			is Response.Error -> Log.d(Constants.TAG, state.message)
		}
	}
}