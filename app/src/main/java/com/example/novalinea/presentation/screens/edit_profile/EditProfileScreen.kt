package com.example.novalinea.presentation.screens.edit_profile

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.DESCRIPTION_PROFILE_PLACEHOLDER
import com.example.novalinea.common.Constants.ERROR_BY_EDIT_PROFILE
import com.example.novalinea.common.Constants.MAX_DESCRIPTION_USER_LENGTH
import com.example.novalinea.common.Constants.MAX_NAME_USER_LENGTH
import com.example.novalinea.common.Constants.NAME_PLACEHOLDER
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.Response
import com.example.novalinea.presentation.components.loader.Loader
import com.example.novalinea.presentation.navigation.BottomNavRoute
import com.example.novalinea.presentation.screens.edit_profile.components.EditProfileTextField
import com.example.novalinea.presentation.screens.edit_profile.components.EditProfileTopBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun EditProfileScreen(
	id: String,
	navController: NavController,
	viewModel: EditProfileViewModel = hiltViewModel()
) {
	val state = viewModel.state.observeAsState(Response.Success(false)).value

	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope()

	Scaffold(
		topBar = {
			EditProfileTopBar(
				onClickBack = {
					openProfile(
						id = id,
						navController = navController
					)
				},
				onClickSave = {
					if (viewModel.name.text.isNotEmpty())
						viewModel.editProfile()
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
		when(state) {
			is Response.Loading -> Loader(background = Color.White)
			is Response.Error -> {
				Log.d(TAG, state.message)
				scope.launch {
					scaffoldState.snackbarHostState.showSnackbar(ERROR_BY_EDIT_PROFILE)
				}
			}
			is Response.Success -> {
				if (state.data) {
					USER.name = viewModel.name.text
					LaunchedEffect(state.data) {
						openProfile(
							id = id,
							navController = navController
						)
					}
				}
			}
		}

		Column(
			modifier = Modifier.padding(horizontal = 20.dp)
		) {
			Spacer(modifier = Modifier.height(20.dp))

			if (viewModel.name.text.isNotEmpty()) {
				Text(
					text = NAME_PLACEHOLDER,
					style = MaterialTheme.typography.caption
				)
			}

			EditProfileTextField(
				value = viewModel.name.text,
				placeholder = NAME_PLACEHOLDER,
				isPlaceholderVisible = viewModel.name.text.isEmpty(),
				onValueChange = {
					if (it.length <= MAX_NAME_USER_LENGTH)
						viewModel.name.text = it
				},
				singleLine = true,
				maxLength = MAX_NAME_USER_LENGTH,
				onSave = {
					if (viewModel.name.text.isNotEmpty())
						viewModel.editProfile()
				}
			)

			Spacer(modifier = Modifier.height(15.dp))

			if (viewModel.description.value.isNotEmpty()) {
				Text(
					text = DESCRIPTION_PROFILE_PLACEHOLDER,
					style = MaterialTheme.typography.caption
				)
			}

			EditProfileTextField(
				value = viewModel.description.value,
				placeholder = DESCRIPTION_PROFILE_PLACEHOLDER,
				isPlaceholderVisible = viewModel.description.value.isEmpty(),
				onValueChange = {
					if (it.length <= MAX_DESCRIPTION_USER_LENGTH)
						viewModel.description.value = it
				},
				singleLine = false,
				maxLength = MAX_DESCRIPTION_USER_LENGTH,
				onSave = {
					if (viewModel.name.text.isNotEmpty())
						viewModel.editProfile()
				}
			)
		}
	}
}

fun openProfile(
	id: String,
	navController: NavController
) {
	navController.popBackStack()
	navController.navigate(
		BottomNavRoute.Profile.route + "?${ARGUMENT_USER_ID_KEY}=${id}"
	)
}