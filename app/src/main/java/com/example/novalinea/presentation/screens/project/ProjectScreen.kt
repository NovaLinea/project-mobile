package com.example.novalinea.presentation.screens.project

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
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.AUTHENTICATION_ROUTE
import com.example.novalinea.common.Constants.BUTTON_SEND
import com.example.novalinea.common.Constants.ERROR_BY_GET_ADDITIONALLY_DATA_PROJECT
import com.example.novalinea.common.Constants.ERROR_BY_SEND_BUY_MESSAGE_PROJECT
import com.example.novalinea.common.Constants.TEXT_BUY_PROJECT
import com.example.novalinea.common.Constants.TITLE_BUY_PROJECT
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.common.Constants.TEXT_BUY_YOURSELF_PROJECT
import com.example.novalinea.common.Constants.TEXT_SUCCESS_SEND_MESSAGE_BUY_PROJECT
import com.example.novalinea.domain.model.ProjectTape
import com.example.novalinea.domain.model.Response
import com.example.novalinea.presentation.components.loader.Loader
import com.example.novalinea.presentation.components.modal.Modal
import com.example.novalinea.presentation.navigation.BottomNavRoute
import com.example.novalinea.presentation.screens.project.components.ProjectBottomBar
import com.example.novalinea.presentation.screens.project.components.ProjectInformation
import com.example.novalinea.presentation.screens.project.components.ProjectTopBar
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProjectScreen(
	project: ProjectTape,
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
				projectPrice = "${project.price}",
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
	) { innerPadding ->

		if (stateProject is Response.Error) {
			Log.d(TAG, stateProject.message)
			scope.launch {
				scaffoldState.snackbarHostState.showSnackbar(ERROR_BY_GET_ADDITIONALLY_DATA_PROJECT)
			}
		}

		when(stateSend) {
			is Response.Loading -> Loader()
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
			ProjectInformation(
				project = project,
				additionallyData = stateProject
			) {
				navController.navigate(
					BottomNavRoute.Profile.route
							+ "?${ARGUMENT_USER_ID_KEY}=${project.creatorID.toString()}"
				)
			}
		}

		Modal(
			openDialog = openDialog,
			title = TITLE_BUY_PROJECT,
			text = TEXT_BUY_PROJECT,
			confirmButton = BUTTON_SEND,
			onClickTrue = {
				viewModel.sendMessage(
					toID = project.creatorID,
					projectID = project.id,
					projectTitle = project.title,
					projectPrice = project.price
				)
			}
		)
	}
}