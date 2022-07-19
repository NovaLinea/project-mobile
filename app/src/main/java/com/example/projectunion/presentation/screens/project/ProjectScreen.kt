package com.example.projectunion.presentation.screens.project

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projectunion.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.projectunion.common.Constants.AUTHENTICATION_ROUTE
import com.example.projectunion.common.Constants.BUTTON_SEND
import com.example.projectunion.common.Constants.ERROR_BY_GET_PROJECT
import com.example.projectunion.common.Constants.ERROR_BY_SEND_BUY_MESSAGE_PROJECT
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.common.Constants.TEXT_BUY_YOURSELF_PROJECT
import com.example.projectunion.common.Constants.TEXT_BUY_PROJECT
import com.example.projectunion.common.Constants.TEXT_SUCCESS_SEND_MESSAGE_BUY_PROJECT
import com.example.projectunion.common.Constants.TITLE_BUY_PROJECT
import com.example.projectunion.common.Constants.USER
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.error.Error
import com.example.projectunion.presentation.components.modal.Modal
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.screens.project.components.ProjectBottomBar
import com.example.projectunion.presentation.screens.project.components.ProjectInformation
import com.example.projectunion.presentation.screens.project.components.ProjectTopBar
import com.example.projectunion.presentation.screens.project.components.ShimmerLoaderProject
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProjectScreen(
	projectID: String,
	price: Int,
	navController: NavController,
	viewModel: ProjectViewModel = hiltViewModel()
) {
	val stateProject = viewModel.stateProject.observeAsState(Response.Success(null)).value
	val stateSend = viewModel.stateSend.observeAsState(Response.Success(false)).value

	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope()

	val openDialog = remember { mutableStateOf(false) }

	Scaffold(
		topBar = { ProjectTopBar(navController) },
		bottomBar = {
			ProjectBottomBar(
				projectPrice = "$price",
				onClickBuy = {
					if (USER.id != null)
						openDialog.value = true
					else
						navController.navigate(AUTHENTICATION_ROUTE)
				}
			)
		},
		scaffoldState = scaffoldState,
		snackbarHost = {
			SnackbarHost(it) { data ->
				Snackbar(
					backgroundColor = Color.White,
					contentColor = Color.Black,
					snackbarData = data
				)
			}
		}
	) {
		innerPadding ->
			when(stateSend) {
				is Response.Loading -> Log.d(TAG, "Loading")
				is Response.Error -> {
					Log.d(TAG, stateSend.message)
					scope.launch {
						if (stateSend.message == TEXT_BUY_YOURSELF_PROJECT)
							scaffoldState.snackbarHostState.showSnackbar(TEXT_BUY_YOURSELF_PROJECT)
						else
							scaffoldState.snackbarHostState.showSnackbar(ERROR_BY_SEND_BUY_MESSAGE_PROJECT)
					}
				}
				is Response.Success -> {
					if (stateSend.data) {
						scope.launch {
							scaffoldState.snackbarHostState.showSnackbar(
								TEXT_SUCCESS_SEND_MESSAGE_BUY_PROJECT
							)
						}
					}
				}
			}

			Box(
				modifier = Modifier.padding(innerPadding)
			) {
				when(stateProject) {
					is Response.Loading -> ShimmerLoaderProject()
					is Response.Error -> {
						Log.d(TAG, stateProject.message)
						Error(message = ERROR_BY_GET_PROJECT) {
							viewModel.getProjectById(projectID)
						}
					}
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
				}
			}

			Modal(
				openDialog = openDialog,
				title = TITLE_BUY_PROJECT,
				text = TEXT_BUY_PROJECT,
				confirmButton = BUTTON_SEND,
				onClickTrue = {
					viewModel.sendMessage()
				}
			)
	}
}