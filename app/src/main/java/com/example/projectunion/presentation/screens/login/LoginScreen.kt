package com.example.projectunion.presentation.screens.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projectunion.common.Constants.ERROR_SERVER
import com.example.projectunion.common.Constants.INVALID_USER
import com.example.projectunion.common.Constants.INVALID_LOGIN_PASSWORD
import com.example.projectunion.common.Constants.INVALID_PASSWORD
import com.example.projectunion.common.Constants.LOGIN
import com.example.projectunion.common.Constants.LOGIN_SCREEN
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.common.Constants.REGISTER
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.common.Constants.USER_NOT_FOUND
import com.example.projectunion.domain.model.Response.*
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.components.email_field.Email
import com.example.projectunion.presentation.components.password_field.Password
import com.example.projectunion.presentation.components.button_action.ButtonActionText
import com.example.projectunion.presentation.components.close_button.CloseButton
import com.example.projectunion.presentation.components.error_field.ErrorField
import com.example.projectunion.presentation.components.text_button_action.TextButtonAction
import com.example.projectunion.presentation.ui.theme.OpenSans

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

	Scaffold {
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
				style = TextStyle(
					color = Color.Black,
					fontFamily = OpenSans,
					fontSize = 25.sp,
					fontWeight = FontWeight.W600
				)
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
					else -> ErrorField(error = ERROR_SERVER)
				}
			}

			Spacer(modifier = Modifier.height(10.dp))

			ButtonActionText(
				LOGIN,
				enabled = state != Loading
						&& viewModel.email.isValidText()
						&& viewModel.password.isValidText()
			) {
				viewModel.loginByEmail()
			}

			TextButtonAction(title = REGISTER) {
				navController.navigate(MainNavRoute.Register.route)
			}
		}
	}
}