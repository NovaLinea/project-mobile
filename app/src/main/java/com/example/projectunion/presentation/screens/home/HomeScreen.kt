package com.example.projectunion.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.background
import com.example.projectunion.common.Constants.TAG
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectunion.R
import com.example.projectunion.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.projectunion.common.Constants.ARGUMENT_PROJECT_ID_KEY
import com.example.projectunion.common.Constants.ARGUMENT_PROJECT_PRICE_KEY
import com.example.projectunion.common.Constants.AUTHENTICATION_ROUTE
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.floating_button.FloatingButton
import com.example.projectunion.presentation.navigation.Router
import com.example.projectunion.presentation.screens.home.components.ProjectItem
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.components.search_field.SearchField
import com.example.projectunion.presentation.screens.home.components.ShimmerLoaderProjects

@Composable
fun HomeScreen(
	externalRouter: Router,
	onClickCreate: () -> Unit,
	viewModel: HomeViewModel = hiltViewModel()
) {
	val state = viewModel.state.observeAsState(Response.Success(emptyList())).value
	val listState = rememberLazyListState()

	Scaffold(
		modifier = Modifier.fillMaxSize(),
		floatingActionButton = {
			FloatingButton(
				imageVector = Icons.Default.Create,
				onClick = {
					if (!viewModel.isAuth)
						externalRouter.navigateTo(AUTHENTICATION_ROUTE)
					else
						onClickCreate()
				}
			)
		}
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(colorResource(id = R.color.background_tape))
		) {
			Row(
				modifier = Modifier
					.padding(top = 15.dp, bottom = 5.dp, start = 12.dp, end = 12.dp)
					.fillMaxWidth()
			) {
				SearchField(
					value = viewModel.search.text,
					onValueChange = {
						viewModel.search.text = it
						viewModel.search.validate()
					},
				)
			}

			when(state) {
				is Response.Loading -> ShimmerLoaderProjects()
				is Response.Error -> Log.d(TAG, state.message)
				is Response.Success -> {
					LazyColumn(
						state = listState,
						modifier = Modifier
							.fillMaxSize()
					) {
						items(state.data) { project ->
							ProjectItem(
								project = project,
								openProfile = {
									externalRouter.navigateTo(
										MainNavRoute.Profile.route
												+ "?$ARGUMENT_USER_ID_KEY=${project.creatorID.toString()}"
									)
								},
								openProject = {
									externalRouter.navigateTo(
										MainNavRoute.Project.route
												+ "?$ARGUMENT_PROJECT_ID_KEY=${project.id}"
												+ "&$ARGUMENT_USER_ID_KEY=${project.creatorID}"
												+ "&$ARGUMENT_PROJECT_PRICE_KEY=${project.price}"
									)
								}
							)
						}
					}
				}
			}
		}
	}
}