package com.example.projectunion.presentation.screens.register

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projectunion.common.Constants
import com.example.projectunion.common.Constants.LOGIN
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.common.Constants.REGISTER
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.screens.components.Email
import com.example.projectunion.presentation.screens.components.Password
import com.example.projectunion.presentation.screens.components.button_action.ButtonAction
import com.example.projectunion.presentation.screens.components.close_button.CloseButton
import com.example.projectunion.presentation.screens.components.name_field.Name
import com.example.projectunion.presentation.screens.components.text_button_action.TextButtonAction
import com.example.projectunion.presentation.screens.components.title.Title

@Composable
fun RegisterScreen(
	navController: NavController,
	viewModel: RegisterViewModel = hiltViewModel()
) {
	var focusManager = LocalFocusManager.current

	when (val response = viewModel.state.value) {
		is Response.Loading -> Log.d(Constants.TAG, "Loading")
		is Response.Success -> {
			if (response.data) {
				LaunchedEffect(response.data) {
					navController.navigate(MAIN_ROUTE)
				}
			}
			else {
				Log.d(Constants.TAG, "Error register")
			}
		}
		is Response.Error -> Log.d(Constants.TAG, "Error ${response.message}")
	}

	Scaffold {
		Box(
			modifier = Modifier.padding(5.dp)
		) {
			CloseButton {
				navController.navigate(MAIN_ROUTE)
			}
		}

		Column(
			modifier = Modifier
				.padding(top = 100.dp, start = 40.dp, end = 40.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Title(Constants.REGISTER_TITLE)

			Column(
				modifier = Modifier.padding(vertical = 5.dp)
			) {
				Name(
					viewModel.name.text,
					viewModel.name.error,
					focusManager
				) {
					viewModel.name.text = it
					viewModel.name.validate()
				}
			}

			Column(
				modifier = Modifier.padding(vertical = 5.dp)
			) {
				Email(
					viewModel.email.text,
					viewModel.email.error,
					focusManager
				) {
					viewModel.email.text = it
					viewModel.email.validate()
				}
			}

			Column(
				modifier = Modifier.padding(vertical = 5.dp)
			) {
				Password(
					viewModel.password.text,
					viewModel.password.error,
					focusManager
				) {
					viewModel.password.text = it
					viewModel.password.validate()
				}
			}

			ButtonAction(
				REGISTER,
				enabled = viewModel.state.value != Response.Loading
						&& viewModel.name.isValidText()
						&& viewModel.email.isValidText()
						&& viewModel.password.isValidText()
			) {
				viewModel.registerByEmail()
			}

			TextButtonAction(title = LOGIN) {
				navController.popBackStack()
			}
		}
	}
}