package com.example.novalinea.presentation.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import com.example.novalinea.common.Constants.TAG
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.novalinea.R
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_DATA
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_ID_KEY
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.AUTHENTICATION_ROUTE
import com.example.novalinea.common.Constants.ERROR_BY_GET_PROJECTS
import com.example.novalinea.domain.model.Response
import com.example.novalinea.presentation.components.error.Error
import com.example.novalinea.presentation.components.floating_button.FloatingButton
import com.example.novalinea.presentation.screens.home.components.ProjectItem
import com.example.novalinea.presentation.navigation.*
import com.example.novalinea.presentation.screens.home.components.HomeTopBar
import com.example.novalinea.presentation.screens.home.components.ShimmerLoaderProjects
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun HomeScreen(
	navController: NavController,
	router: Router?,
	onClickCreate: () -> Unit,
	viewModel: HomeViewModel = hiltViewModel()
) {
	//val listProjects = mutableStateListOf<ProjectTape>()
	val stateGet = viewModel.stateGet.observeAsState(Response.Success(emptyList())).value
	//val stateUpdate = viewModel.stateUpdate.observeAsState(Response.Success(emptyList())).value
	//val stateLoad = viewModel.stateLoad.observeAsState(Response.Success(emptyList())).value

	val listState = rememberLazyListState()

	var isRefreshing by remember { mutableStateOf(false) }
	val swipeState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

	Scaffold(
		topBar = { HomeTopBar() },
		modifier = Modifier.fillMaxSize(),
		floatingActionButton = {
			FloatingButton(
				imageVector = Icons.Default.Create,
				onClick = {
					if (!viewModel.isAuth)
						router?.routeTo(AUTHENTICATION_ROUTE)
					else
						onClickCreate()
				}
			)
		}
	) {
		when(stateGet) {
			is Response.Loading -> ShimmerLoaderProjects()
			is Response.Error -> {
				Log.d(TAG, stateGet.message)
				Error(message = ERROR_BY_GET_PROJECTS) {
					viewModel.getProjects()
				}
			}
			is Response.Success -> {
				//listProjects.addAll(stateGet.data)

				SwipeRefresh(
					state = swipeState,
					onRefresh = {
						viewModel.getProjects()
						/*isRefreshing = true
						viewModel.updateProjects()
						when(stateUpdate) {
							else -> Log.d(TAG, stateUpdate.toString())
						}
						if (stateUpdate is Response.Success) {
							listProjects.addAll(stateUpdate.data)
						}
						isRefreshing = false*/
					}
				) {
					LazyColumn(
						state = listState,
						modifier = Modifier
							.fillMaxSize()
							.background(colorResource(id = R.color.background_tape))
							/*.background(
								brush = Brush.verticalGradient(
									colors = listOf(
										Color.White,
										colorResource(id = R.color.app_background)
									)
								)
							)*/
					) {
						items(
							items = stateGet.data,
							//items = listProjects,
							key = { project ->
								project.hashCode()
							}
						) { project ->
							/*if (index == stateGet.data.size - 1) {
								viewModel.loadProjects()
							}*/
							ProjectItem(
								project = project,
								openProfile = {
									router?.routeTo(
										BottomNavRoute.Profile.route
												+ "?$ARGUMENT_USER_ID_KEY=${project.creatorID.toString()}"
									)
								},
								openProject = {
									router?.routeTo(
										HomeNavRoute.Project.route
												+ "?$ARGUMENT_PROJECT_ID_KEY=${project.id}",
										Pair(ARGUMENT_PROJECT_DATA, project)
									)
								}
							)

							/*when(stateLoad) {
								is Response.Loading -> {}
								is Response.Error -> {}
								is Response.Success -> listProjects.addAll(stateLoad.data)
							}*/
						}
					}
				}
			}
		}
	}
}