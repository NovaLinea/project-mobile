package com.example.novalinea.presentation.screens.login

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
import com.example.novalinea.common.Constants.ERROR_SERVER
import com.example.novalinea.common.Constants.ERROR_VERIFY_EMAIL
import com.example.novalinea.common.Constants.INVALID_USER
import com.example.novalinea.common.Constants.INVALID_LOGIN_PASSWORD
import com.example.novalinea.common.Constants.INVALID_PASSWORD
import com.example.novalinea.common.Constants.BUTTON_LOGIN
import com.example.novalinea.common.Constants.LOGIN_SCREEN
import com.example.novalinea.common.Constants.MAIN_ROUTE
import com.example.novalinea.common.Constants.BUTTON_REGISTER
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.common.Constants.USER_NOT_FOUND
import com.example.novalinea.domain.model.Response.*
import com.example.novalinea.presentation.components.email_field.Email
import com.example.novalinea.presentation.components.password_field.Password
import com.example.novalinea.presentation.components.button_action.ButtonActionText
import com.example.novalinea.presentation.components.close_button.CloseButton
import com.example.novalinea.presentation.components.error_field.ErrorField
import com.example.novalinea.presentation.components.text_button_action.TextButtonAction
import com.example.novalinea.presentation.navigation.AuthNavRoute

@Composable
fun LoginScreen(
	navController: NavController,
	viewModel: LoginViewModel = hiltViewModel()
) {
	val state = viewModel.state.observeAsState(Success(false)).value
	when(state) {
		is Loading -> Log.d(TAG, "Loading")
		is Success -> {
			if (state.data) {
				LaunchedEffect(state.data) {
					navController.navigate(MAIN_ROUTE)
				}
			}
		}
		is Error -> Log.d(TAG, state.message)
	}

	val focusManager = LocalFocusManager.current

	Box(
		modifier = Modifier.fillMaxSize()
	) {
		Box(modifier = Modifier.padding(5.dp)) {
			CloseButton {
				navController.popBackStack()
			}
		}

		Column(
			modifier = Modifier
				.padding(top = 80.dp, start = 40.dp, end = 40.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = LOGIN_SCREEN,
				style = MaterialTheme.typography.h3
			)
			Spacer(modifier = Modifier.height(30.dp))

			Email(
				viewModel.email.text,
				viewModel.email.error,
				focusManager
			) {
				viewModel.email.text = it
				viewModel.email.validate()
			}

			Spacer(modifier = Modifier.height(10.dp))

			Password(
				viewModel.password.text,
				viewModel.password.error,
				focusManager
			) {
				viewModel.password.text = it
				viewModel.password.validate()
			}

			if (state is Error) {
				Spacer(modifier = Modifier.height(5.dp))

				when(state.message) {
					USER_NOT_FOUND -> ErrorField(error = INVALID_USER)
					INVALID_LOGIN_PASSWORD -> ErrorField(error = INVALID_PASSWORD)
					ERROR_VERIFY_EMAIL -> ErrorField(error = ERROR_VERIFY_EMAIL)
					else -> ErrorField(error = ERROR_SERVER)
				}
			}

			Spacer(modifier = Modifier.height(10.dp))

			ButtonActionText(
				BUTTON_LOGIN,
				enabled = state != Loading
						&& viewModel.email.isValidText()
						&& viewModel.password.isValidText()
			) {
				viewModel.loginByEmail()
			}

			TextButtonAction(title = BUTTON_REGISTER) {
				navController.navigate(AuthNavRoute.Register.route)
			}
		}
	}
}