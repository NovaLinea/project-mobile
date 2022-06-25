package com.example.projectunion.presentation.screens.register

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
import com.example.projectunion.common.Constants.EMAIL_IS_USED
import com.example.projectunion.common.Constants.ERROR_SERVER
import com.example.projectunion.common.Constants.INVALID_REGISTER
import com.example.projectunion.common.Constants.LOGIN
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.common.Constants.REGISTER
import com.example.projectunion.common.Constants.REGISTER_SCREEN
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.email_field.Email
import com.example.projectunion.presentation.components.password_field.Password
import com.example.projectunion.presentation.components.button_action.ButtonActionText
import com.example.projectunion.presentation.components.close_button.CloseButton
import com.example.projectunion.presentation.components.error_field.ErrorField
import com.example.projectunion.presentation.components.name_field.Name
import com.example.projectunion.presentation.components.text_button_action.TextButtonAction
import com.example.projectunion.presentation.components.title.Title

@Composable
fun RegisterScreen(
	navController: NavController,
	viewModel: RegisterViewModel = hiltViewModel()
) {
	val state = viewModel.state.observeAsState(Response.Success(false)).value
	when(state) {
		is Response.Loading -> Log.d(Constants.TAG, "Loading")
		is Response.Success -> {
			if (state.data) {
				LaunchedEffect(state.data) {
					navController.navigate(MAIN_ROUTE)
				}
			}
		}
		is Response.Error -> Log.d(Constants.TAG, state.message)
	}

	var focusManager = LocalFocusManager.current

	Scaffold {
		Box(modifier = Modifier.padding(5.dp)) {
			CloseButton {
				navController.navigate(MAIN_ROUTE)
			}
		}

		Column(
			modifier = Modifier.padding(top = 80.dp, start = 40.dp, end = 40.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Title(REGISTER_SCREEN)
			Spacer(modifier = Modifier.height(30.dp))

			Name(
				viewModel.name.text,
				viewModel.name.error,
				focusManager
			) {
				viewModel.name.text = it
				viewModel.name.validate()
			}

			Spacer(modifier = Modifier.height(10.dp))

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

			if (state is Response.Error) {
				Spacer(modifier = Modifier.height(5.dp))
				if (state.message == EMAIL_IS_USED)
					ErrorField(error = INVALID_REGISTER)
				else
					ErrorField(error = ERROR_SERVER)
			}

			Spacer(modifier = Modifier.height(7.dp))

			ButtonActionText(
				REGISTER,
				enabled = state != Response.Loading
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