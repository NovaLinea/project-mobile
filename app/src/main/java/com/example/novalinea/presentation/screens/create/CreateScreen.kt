package com.example.novalinea.presentation.screens.create

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.novalinea.common.Constants.ERROR_BY_CREATE_PROJECT
import com.example.novalinea.common.Constants.MAIN_ROUTE
import com.example.novalinea.common.Constants.MAX_DESCRIPTION_PROJECT_LENGTH
import com.example.novalinea.common.Constants.MAX_TITLE_PROJECT_LENGTH
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.StepsCreateProject
import com.example.novalinea.domain.model.TypesProject
import com.example.novalinea.presentation.navigation.PresentNested
import com.example.novalinea.presentation.screens.create.components.AdditionallyInformationProject
import com.example.novalinea.presentation.screens.create.components.CreateTopBar
import com.example.novalinea.presentation.screens.create.components.MainInformationProject
import com.example.novalinea.presentation.screens.create.components.TypeSelection
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter",
	"UnrememberedMutableState"
)
@Composable
fun CreateScreen(
	navController: NavController,
	viewModel: CreateViewModel = hiltViewModel()
) {
	val stateCreate = viewModel.stateCreate.observeAsState(Response.Success(false)).value

	val stateSteps = remember {
		mutableStateListOf(true, false, false)
	}
	var step = remember {
		mutableStateOf(StepsCreateProject.TYPE_PROJECT)
	}

	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope()

	Scaffold(
		topBar = {
			CreateTopBar(
				stateSteps = stateSteps,
				isShowBack = step.value != StepsCreateProject.TYPE_PROJECT,
				onClickBack = {
					when(step.value) {
						StepsCreateProject.MAIN_INFORMATION -> {
							step.value = StepsCreateProject.TYPE_PROJECT
							stateSteps[1] = false
						}
						StepsCreateProject.ADDITIONALLY_INFORMATION -> {
							step.value = StepsCreateProject.MAIN_INFORMATION
							stateSteps[2] = false
						}
					}
				},
				onClickClose = {
					navController.popBackStack()
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
		when(stateCreate) {
			is Response.Loading -> Log.d(TAG, "Loading")
			is Response.Error -> {
				Log.d(TAG, stateCreate.message)
				scope.launch {
					scaffoldState.snackbarHostState.showSnackbar(ERROR_BY_CREATE_PROJECT)
				}
			}
			is Response.Success -> {
				if (stateCreate.data) {
					LaunchedEffect(stateCreate.data) {
						navController.navigate(MAIN_ROUTE)
					}
				}
			}
		}

		when(step.value) {
			StepsCreateProject.TYPE_PROJECT -> {
				PresentNested {
					TypeSelection { typeProject ->
						viewModel.type.value = typeProject
						stateSteps[1] = true
						step.value = StepsCreateProject.MAIN_INFORMATION
					}
				}
			}

			StepsCreateProject.MAIN_INFORMATION -> {
				PresentNested {
					MainInformationProject(
						title = viewModel.title.text,
						onTitleChange = { title ->
							if (title.length <= MAX_TITLE_PROJECT_LENGTH)
								viewModel.title.text = title
						},
						description = viewModel.description.text,
						onDescriptionChange = { description ->
							if (description.length <= MAX_DESCRIPTION_PROJECT_LENGTH)
								viewModel.description.text = description
						},
						enabled = viewModel.title.isValidText() && viewModel.description.isValidText()
					) {
						stateSteps[2] = true
						step.value = StepsCreateProject.ADDITIONALLY_INFORMATION
					}
				}
			}

			StepsCreateProject.ADDITIONALLY_INFORMATION -> {
				PresentNested {
					AdditionallyInformationProject(
						type = viewModel.type.value,
						price = viewModel.price.text,
						onPriceChange = { price ->
							if (price.isEmpty())
								viewModel.price.text = ""
							else {
								viewModel.price.text = when(price.replace(" ", "").toIntOrNull()) {
									null -> viewModel.price.text
									else -> price
								}
							}
						},
						images = viewModel.images,
						onAddImage = { uri ->
							viewModel.addImageProject(uri)
						},
						onDeleteImage = { index ->
							viewModel.deleteImageProject(index)
						},
						enabled = stateCreate != Response.Loading &&
								if (viewModel.type.value == TypesProject.SALE) viewModel.price.isValidText()
								else true,
						onCreate = {
							viewModel.createProject()
						}
					)
				}
			}
		}
	}
}