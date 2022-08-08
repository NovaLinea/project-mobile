package com.example.novalinea.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.background
import com.example.novalinea.common.Constants.TAG
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.novalinea.R
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_DATA
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_ID_KEY
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.AUTHENTICATION_ROUTE
import com.example.novalinea.common.Constants.ERROR_BY_GET_PROJECTS
import com.example.novalinea.presentation.components.error.Error
import com.example.novalinea.presentation.components.floating_button.FloatingButton
import com.example.novalinea.presentation.components.loader.Loader
import com.example.novalinea.presentation.screens.home.components.ProjectItem
import com.example.novalinea.presentation.navigation.*
import com.example.novalinea.presentation.screens.home.components.HomeTopBar
import com.example.novalinea.presentation.screens.home.components.ShimmerLoaderProjects
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
	router: Router?,
	viewModel: HomeViewModel = hiltViewModel()
) {
	val projects = viewModel.projects.collectAsLazyPagingItems()

	val scaffoldState = rememberScaffoldState()
	val listState = rememberLazyListState()
	val scope = rememberCoroutineScope()

	//var isRefreshing by remember { mutableStateOf(false) }
	//val swipeState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

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
						router?.routeTo(HomeNavRoute.Create.route)
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
	) { padding ->
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(padding)
		) {
			/*SwipeRefresh(
				state = swipeState,
				onRefresh = {
					projects.refresh()
				}
			) {

			}*/

			LazyColumn(
				state = listState,
				modifier = Modifier
					.fillMaxSize()
					.background(colorResource(id = R.color.app_background_tape))
			) {
				items(
					items = projects
				) { project ->
					project?.let {
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
					}
				}

				projects.loadState.apply {
					when {
						refresh is LoadState.Loading -> item {
							ShimmerLoaderProjects()
						}
						refresh is LoadState.Error -> item {
							Log.d(TAG, (refresh as LoadState.Error).toString())
							Error(message = ERROR_BY_GET_PROJECTS) {
								projects.retry()
							}
						}
						append is LoadState.Loading -> item {
							Box(
								modifier = Modifier
									.height(75.dp)
									.fillMaxWidth()
							) {
								Loader(background = Color.White)
							}
						}
						append is LoadState.Error -> {
							Log.d(TAG, (append as LoadState.Error).toString())
							scope.launch {
								scaffoldState.snackbarHostState.showSnackbar(ERROR_BY_GET_PROJECTS)
							}
						}
					}
				}
			}
		}
	}
}