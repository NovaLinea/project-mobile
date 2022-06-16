package com.example.projectunion.presentation.screens.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projectunion.common.Constants
import com.example.projectunion.common.Constants.INVALID_LOGIN
import com.example.projectunion.common.Constants.LOGIN
import com.example.projectunion.common.Constants.LOGIN_TITLE
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.domain.model.Response.*
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.components.email_field.Email
import com.example.projectunion.presentation.components.password_field.Password
import com.example.projectunion.presentation.components.button_action.ButtonAction
import com.example.projectunion.presentation.components.close_button.CloseButton
import com.example.projectunion.presentation.components.error_field.ErrorField
import com.example.projectunion.presentation.components.text_button_action.TextButtonAction
import com.example.projectunion.presentation.components.title.Title

@Composable
fun LoginScreen(
	navController: NavController,
	viewModel: LoginViewModel = hiltViewModel()
) {
	val state = viewModel.state.observeAsState(Success(false)).value

	//when (val state = viewModel.state.value)
	when(state) {
		is Loading -> Log.d(Constants.TAG, "Loading")
		is Success -> {
			if (state.data) {
				LaunchedEffect(state.data) {
					navController.navigate(MAIN_ROUTE)
				}
			}
		}
		is Error -> Log.d(Constants.TAG, "Error ${state.message}")
	}

	var focusManager = LocalFocusManager.current

	Scaffold {
		Box(modifier = Modifier.padding(5.dp)) {
			CloseButton {
				navController.navigate(MAIN_ROUTE)
			}
		}

		Column(
			modifier = Modifier
				.padding(top = 80.dp, start = 40.dp, end = 40.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Box(modifier = Modifier.padding(bottom = 25.dp)) {
				Title(LOGIN_TITLE)
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

			if (state is Error)
				ErrorField(error = INVALID_LOGIN)

			ButtonAction(
				LOGIN,
				enabled = viewModel.state.value != Loading
						&& viewModel.email.isValidText()
						&& viewModel.password.isValidText()
			) {
				viewModel.loginByEmail()
			}

			TextButtonAction(title = Constants.REGISTER) {
				navController.navigate(MainNavRoute.Register.route)
			}
		}
	}
}