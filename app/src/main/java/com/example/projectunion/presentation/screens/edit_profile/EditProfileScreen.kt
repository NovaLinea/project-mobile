package com.example.projectunion.presentation.screens.edit_profile

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
import com.example.projectunion.common.Constants.DESCRIPTION_PROFILE
import com.example.projectunion.common.Constants.DESCRIPTION_PROJECT_PLACEHOLDER
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.common.Constants.NAME_PLACEHOLDER
import com.example.projectunion.common.Constants.PRICE_PROJECT_PLACEHOLDER
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.common.Constants.TITLE_PROJECT_PLACEHOLDER
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.screens.create.components.CreateBottomBar
import com.example.projectunion.presentation.screens.create.components.create_text_field.CreatePriceField
import com.example.projectunion.presentation.screens.create.components.create_text_field.CreateTextField
import com.example.projectunion.presentation.screens.create.components.CreateTopBar
import com.example.projectunion.presentation.screens.edit_profile.components.EditProfileTextField
import com.example.projectunion.presentation.screens.edit_profile.components.EditProfileTopBar

@Composable
fun EditProfileScreen(
	name: String?,
	description: String?,
	navController: NavController,
	viewModel: EditProfileViewModel = hiltViewModel()
) {
	/*val state = viewModel.state.observeAsState(Response.Success(false)).value
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
	}*/

	val maxCharName = 30
	val maxCharDescription = 70

	Scaffold(
		topBar = {
			EditProfileTopBar(
				navController = navController,
				onClickSave = {

				}
			)
		},
	) {
		Column(
			modifier = Modifier.padding(horizontal = 20.dp)
		) {
			Spacer(modifier = Modifier.height(20.dp))

			name?.let { name ->
				EditProfileTextField(
					value = name,
					placeholder = NAME_PLACEHOLDER,
					isPlaceholderVisible = name.isEmpty(),
					onValueChange = {},
					singleLine = true
				)
			}

			Spacer(modifier = Modifier.height(20.dp))

			description?.let { description ->
				EditProfileTextField(
					value = description,
					placeholder = DESCRIPTION_PROFILE,
					isPlaceholderVisible = description.isEmpty(),
					onValueChange = {},
					singleLine = false
				)
			}
		}
	}
}