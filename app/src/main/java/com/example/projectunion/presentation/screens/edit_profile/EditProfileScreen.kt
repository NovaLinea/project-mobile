package com.example.projectunion.presentation.screens.edit_profile

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projectunion.common.Constants
import com.example.projectunion.common.Constants.ARGUMENT_PROFILE_ID_KEY
import com.example.projectunion.common.Constants.DESCRIPTION_PROFILE
import com.example.projectunion.common.Constants.NAME_PLACEHOLDER
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.loader.Loader
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.screens.edit_profile.components.EditProfileTextField
import com.example.projectunion.presentation.screens.edit_profile.components.EditProfileTopBar

@Composable
fun EditProfileScreen(
	id: String,
	navController: NavController,
	viewModel: EditProfileViewModel = hiltViewModel()
) {
	when(val state = viewModel.state.observeAsState(Response.Success(false)).value) {
		is Response.Loading -> Loader()
		is Response.Success -> {
			if (state.data) {
				LaunchedEffect(state.data) {
					navController.popBackStack()
					openProfile(
						id = id,
						navController = navController
					)
				}
			}
		}
		is Response.Error -> Log.d(TAG, state.message)
	}

	val maxCharName = 30
	val maxCharDescription = 70

	Scaffold(
		topBar = {
			EditProfileTopBar(
				onClickBack = {
					navController.popBackStack()
					openProfile(id, navController)
				},
				onClickSave = {
					if (viewModel.name.text.isNotEmpty())
						viewModel.editProfile()
				}
			)
		},
	) {
		Column(
			modifier = Modifier.padding(horizontal = 20.dp)
		) {
			Spacer(modifier = Modifier.height(20.dp))

			EditProfileTextField(
				value = viewModel.name.text,
				placeholder = NAME_PLACEHOLDER,
				isPlaceholderVisible = viewModel.name.text.isEmpty(),
				onValueChange = {
					if (it.length < maxCharName)
						viewModel.name.text = it
					else
						viewModel.name.text = it.substring(0, maxCharName)
				},
				singleLine = true,
				onSave = {
					if (viewModel.name.text.isNotEmpty())
						viewModel.editProfile()
				}
			)

			Spacer(modifier = Modifier.height(15.dp))

			EditProfileTextField(
				value = viewModel.description.value,
				placeholder = DESCRIPTION_PROFILE,
				isPlaceholderVisible = viewModel.description.value.isEmpty(),
				onValueChange = {
					if (it.length < maxCharDescription)
						viewModel.description.value = it
					else
						viewModel.description.value = it.substring(0, maxCharDescription)
				},
				singleLine = false,
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
	navController.navigate(
		MainNavRoute.Profile.route + "?${ARGUMENT_PROFILE_ID_KEY}=${id}"
	)
}