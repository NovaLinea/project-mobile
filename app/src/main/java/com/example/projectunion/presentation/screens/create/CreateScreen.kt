package com.example.projectunion.presentation.screens.create

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projectunion.common.Constants.DESCRIPTION_PROJECT_PLACEHOLDER
import com.example.projectunion.common.Constants.ERROR_BY_CREATE_PROJECT
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.common.Constants.MAX_DESCRIPTION_PROJECT_LENGTH
import com.example.projectunion.common.Constants.MAX_TITLE_PROJECT_LENGTH
import com.example.projectunion.common.Constants.PRICE_PROJECT_PLACEHOLDER
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.common.Constants.TITLE_PROJECT_PLACEHOLDER
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.screens.create.components.CreateBottomBar
import com.example.projectunion.presentation.screens.create.components.create_text_field.CreatePriceField
import com.example.projectunion.presentation.screens.create.components.create_text_field.CreateTextField
import com.example.projectunion.presentation.screens.create.components.CreateTopBar
import com.example.projectunion.presentation.ui.theme.OpenSans
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CreateScreen(
	typeProject: String,
	navController: NavController,
	viewModel: CreateViewModel = hiltViewModel()
) {
	val stateCreate = viewModel.stateCreate.observeAsState(Response.Success(false)).value

	val focusManager = LocalFocusManager.current
	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope()

	Scaffold(
		topBar = {
			CreateTopBar() {
				navController.popBackStack()
			}
		 },
		bottomBar = {
			CreateBottomBar(
				images = viewModel.images,
				enabledCreate = stateCreate != Response.Loading
						&& viewModel.title.isValidText()
						&& viewModel.price.isValidText(),
				onAddImage = { uri ->
					viewModel.addImageProject(uri)
				},
				onDeleteImage = { index ->
					viewModel.deleteImageProject(index)
				},
				onCreate = {
					viewModel.createProject()
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

			Box(
				modifier = Modifier.padding(innerPadding)
			) {
				Column(
					modifier = Modifier
						.padding(horizontal = 15.dp, vertical = 7.dp)
						.verticalScroll(rememberScrollState())
				) {
					CreateTextField(
						value = viewModel.title.text,
						placeholder = TITLE_PROJECT_PLACEHOLDER,
						isPlaceholderVisible = viewModel.title.text.isEmpty(),
						onValueChange = {
							if (it.length < MAX_TITLE_PROJECT_LENGTH)
								viewModel.title.text = it
							/*else
								viewModel.title.text = it.substring(0, MAX_TITLE_PROJECT_LENGTH)*/
						},
						textStyle = MaterialTheme.typography.h6
					)

					Spacer(modifier = Modifier.height(15.dp))

					CreatePriceField(
						value = viewModel.price.text,
						placeholder = PRICE_PROJECT_PLACEHOLDER,
						onValueChange = {
							if (it.isEmpty()) {
								viewModel.price.text = ""
							}
							else {
								viewModel.price.text = when(it.toIntOrNull()) {
									null -> viewModel.price.text
									else -> it
								}
							}
						},
						textStyle = TextStyle(
							fontFamily = OpenSans,
							fontWeight = FontWeight.W400,
							fontSize = 17.sp
						),
						focusManager = focusManager
					)

					Spacer(modifier = Modifier.height(15.dp))

					CreateTextField(
						value = viewModel.description.text,
						placeholder = DESCRIPTION_PROJECT_PLACEHOLDER,
						isPlaceholderVisible = viewModel.description.text.isEmpty(),
						onValueChange = {
							if (it.length < MAX_DESCRIPTION_PROJECT_LENGTH)
								viewModel.description.text = it
						},
						textStyle = TextStyle(
							fontFamily = OpenSans,
							fontWeight = FontWeight.W400,
							fontSize = 16.sp
						)
					)
				}
			}
	}
}