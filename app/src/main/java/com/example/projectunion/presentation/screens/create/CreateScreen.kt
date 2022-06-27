package com.example.projectunion.presentation.screens.create

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projectunion.common.Constants.DESCRIPTION_PROJECT_PLACEHOLDER
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.common.Constants.PRICE_PROJECT_PLACEHOLDER
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.common.Constants.TITLE_PROJECT_PLACEHOLDER
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.screens.create.components.CreateBottomBar
import com.example.projectunion.presentation.screens.create.components.create_text_field.CreatePriceField
import com.example.projectunion.presentation.screens.create.components.create_text_field.CreateTextField
import com.example.projectunion.presentation.screens.create.components.CreateTopBar


@Composable
fun CreateScreen(
	typeProject: String,
	navController: NavController,
	viewModel: CreateViewModel = hiltViewModel()
) {
	val state = viewModel.state.observeAsState(Response.Success(false)).value
	when(state) {
		is Response.Loading -> Log.d(TAG, "Loading")
		is Response.Success -> {
			if (state.data) {
				LaunchedEffect(state.data) {
					navController.navigate(MAIN_ROUTE)
				}
			}
		}
		is Response.Error -> Log.d(TAG, state.message)
	}

	val maxCharTitle = 120
	val maxCharDescription = 10000

	Scaffold(
		topBar = { CreateTopBar(navController) },
		bottomBar = {
			CreateBottomBar(
				images = viewModel.images,
				enabledCreate = state != Response.Loading
						&& viewModel.title.isValidText()
						&& viewModel.description.isValidText()
						&& viewModel.price.isValidText(),
				onClickCreate = {
					viewModel.createProject()
				}
			)
		}
	) {
		innerPadding ->
			Box(
				modifier = Modifier.padding(innerPadding)
			) {
				Column(
					modifier = Modifier
						.padding(horizontal = 15.dp, vertical = 10.dp)
						.verticalScroll(rememberScrollState())
				) {
					CreateTextField(
						value = viewModel.title.text,
						placeholder = TITLE_PROJECT_PLACEHOLDER,
						isPlaceholderVisible = viewModel.title.text.isEmpty(),
						onValueChange = {
							if (it.length < maxCharTitle)
								viewModel.title.text = it
							else
								viewModel.title.text = it.substring(0, maxCharTitle)
						},
						textStyle = MaterialTheme.typography.h5
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
						isPlaceholderVisible = viewModel.price.text.isEmpty(),
						textStyle = MaterialTheme.typography.body1
					)

					Spacer(modifier = Modifier.height(15.dp))

					CreateTextField(
						value = viewModel.description.text,
						placeholder = DESCRIPTION_PROJECT_PLACEHOLDER,
						isPlaceholderVisible = viewModel.description.text.isEmpty(),
						onValueChange = {
							if (it.length < maxCharDescription)
								viewModel.description.text = it
							else
								viewModel.description.text = it.substring(0, maxCharDescription)
						},
						textStyle = MaterialTheme.typography.body1
					)
				}
			}
	}
}