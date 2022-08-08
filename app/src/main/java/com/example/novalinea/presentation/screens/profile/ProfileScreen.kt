package com.example.novalinea.presentation.screens.profile

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.novalinea.R
import com.example.novalinea.common.Constants
import com.example.novalinea.common.Constants.ARGUMENT_PHOTOS_KEY
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_DATA
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_ID_KEY
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.ERROR_BY_GET_PROFILE
import com.example.novalinea.common.Constants.ERROR_BY_GET_PROJECTS
import com.example.novalinea.common.Constants.ERROR_BY_LOGOUT
import com.example.novalinea.common.Constants.ERROR_BY_UPDATE_PHOTO
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.common.Constants.TITLE_NO_PROJECTS
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.Photos
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.UserProfile
import com.example.novalinea.presentation.components.bottom_sheets.BottomSheetScreen
import com.example.novalinea.presentation.components.error.Error
import com.example.novalinea.presentation.components.loader.Loader
import com.example.novalinea.presentation.navigation.*
import com.example.novalinea.presentation.screens.home.components.ShimmerLoaderProjects
import com.example.novalinea.presentation.screens.home.components.ProjectItem
import com.example.novalinea.presentation.screens.profile.components.ProfileInformation
import com.example.novalinea.presentation.screens.profile.components.ProfileTopBar
import com.example.novalinea.presentation.screens.profile.components.ShimmerLoaderProfile
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun ProfileScreen(
	userID: String,
	navController: NavController,
	router: Router? = null,
	showBottomSheet: (BottomSheetScreen) -> Unit,
	viewModel: ProfileViewModel = hiltViewModel()
) {
	val projects = viewModel.projects?.collectAsLazyPagingItems()

	val stateProfile = viewModel.stateProfile.observeAsState(Response.Success(null)).value
	val statePhoto = viewModel.statePhoto.observeAsState(Response.Success(null)).value
	val photoProfile = viewModel.photoProfile.observeAsState(null).value
	val stateLogout = viewModel.stateLogout.observeAsState(Response.Success(false)).value

	var countProjects = 0
	val listState = rememberLazyListState()

	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope()

	Scaffold(
		topBar = {
			ProfileTopBar(
				isBack = router == null,
				onClickBack = { navController.popBackStack() },
				showBottomSheet = {
					showBottomSheet(
						BottomSheetScreen.ProfileActions(
							userID = userID,
							navController = navController,
							onLogout = {
								viewModel.logout()
							}
						)
					)
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
		if (statePhoto is Response.Error) {
			Log.d(TAG, statePhoto.message)
			scope.launch {
				scaffoldState.snackbarHostState.showSnackbar(ERROR_BY_UPDATE_PHOTO)
			}
		}

		when(stateLogout) {
			is Response.Loading -> Loader()
			is Response.Error -> {
				Log.d(TAG, stateLogout.message)
				scope.launch {
					scaffoldState.snackbarHostState.showSnackbar(ERROR_BY_LOGOUT)
				}
			}
			is Response.Success -> {
				if (stateLogout.data) {
					LaunchedEffect(stateLogout.data) {
						if (router != null)
							router.routeTo(Constants.MAIN_ROUTE)
						else
							navController?.navigate(Constants.MAIN_ROUTE)
						USER = UserProfile()
					}
				}
			}
		}

		LazyColumn(
			state = listState,
			modifier = Modifier
				.fillMaxSize()
				.background(colorResource(id = R.color.app_background_tape))
		) {
			item {
				when(stateProfile) {
					is Response.Loading -> ShimmerLoaderProfile()
					is Response.Error -> {
						Log.d(TAG, stateProfile.message)
						Box(
							modifier = Modifier.height(250.dp)
						) {
							Error(message = ERROR_BY_GET_PROFILE) {
								viewModel.getProfileData(userID)
							}
						}
					}
					is Response.Success -> {
						stateProfile.data?.let { user ->
							ProfileInformation(
								user = user,
								photoProfile = photoProfile,
								isLoadingPhoto = statePhoto is Response.Loading,
								countProjects = countProjects,
								navController = navController
							) {
								if (USER.id == userID) {
									showBottomSheet(
										BottomSheetScreen.PhotoProfileActions(
											photoIsEmpty = photoProfile == null,
											onOpenPhoto = {
												openPhoto(
													photoProfile = photoProfile,
													router = router,
													navController = navController
												)
											},
											onChangePhoto = { uri ->
												viewModel.updatePhoto(uri)
											},
											onDeletePhoto = {
												viewModel.deletePhoto()
											}
										)
									)
								}
								else {
									openPhoto(
										photoProfile = photoProfile,
										router = router,
										navController = navController
									)
								}
							}
						}
					}
				}

				Spacer(modifier = Modifier.height(5.dp))
			}

			projects?.let {
				if (projects.itemCount == 0 && projects.loadState.refresh is LoadState.NotLoading) {
					item {
						Column(
							modifier = Modifier.fillMaxSize(),
							horizontalAlignment = Alignment.CenterHorizontally
						) {
							Text(
								modifier = Modifier.padding(top = 50.dp),
								text = TITLE_NO_PROJECTS,
								style = MaterialTheme.typography.body2
							)
						}
					}
				}
				else {
					countProjects = projects.itemCount

					items(
						items = projects,
						key = { project ->
							project.id.hashCode()
						}
					) { project ->
						project?.let {
							ProjectItem(
								project = project,
								openProfile = {
									navController.navigate(
										BottomNavRoute.Profile.route
												+ "?${ARGUMENT_USER_ID_KEY}=${project.creatorID}"
									)
								},
								openProject = {
									if (router != null) {
										router.routeTo(
											HomeNavRoute.Project.route
													+ "?$ARGUMENT_PROJECT_ID_KEY=${project.id}",
											Pair(ARGUMENT_PROJECT_DATA, project)
										)
									}
									else {
										navController.navigate(
											HomeNavRoute.Project.route
													+ "?$ARGUMENT_PROJECT_ID_KEY=${project.id}",
											Pair(ARGUMENT_PROJECT_DATA, project)
										)
									}
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
								Box(
									modifier = Modifier.padding(top = 50.dp)
								) {
									Error(
										message = ERROR_BY_GET_PROJECTS,
										background = colorResource(id = R.color.app_background_tape)
									) {
										projects.retry()
									}
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
}

fun openPhoto(
	photoProfile: String?,
	navController: NavController,
	router: Router? = null,

) {
	if (photoProfile != null) {
		val photos = Photos(photos = listOf(photoProfile))
		if (router != null) {
			router.routeTo(
				ProfileNavRoute.ViewingPhoto.route,
				Pair(ARGUMENT_PHOTOS_KEY, photos)
			)
		} else {
			navController.navigate(
				ProfileNavRoute.ViewingPhoto.route,
				Pair(ARGUMENT_PHOTOS_KEY, photos)
			)
		}
	}
}