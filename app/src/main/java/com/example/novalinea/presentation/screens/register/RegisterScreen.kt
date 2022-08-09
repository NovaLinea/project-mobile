package com.example.novalinea.presentation.screens.register

import android.annotation.SuppressLint
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
import com.example.novalinea.common.Constants.ARGUMENT_USER_EMAIL_KEY
import com.example.novalinea.common.Constants.AUTHENTICATION_ROUTE
import com.example.novalinea.common.Constants.EMAIL_IS_USED
import com.example.novalinea.common.Constants.ERROR_SERVER
import com.example.novalinea.common.Constants.INVALID_REGISTER
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.domain.model.Response.*
import com.example.novalinea.domain.model.StepsRegister
import com.example.novalinea.presentation.components.email_field.Email
import com.example.novalinea.presentation.components.password_field.Password
import com.example.novalinea.presentation.components.error_field.ErrorField
import com.example.novalinea.presentation.components.login_field.Login
import com.example.novalinea.presentation.components.name_field.Name
import com.example.novalinea.presentation.components.top_bar.StepsTopBar
import com.example.novalinea.presentation.navigation.AuthNavRoute
import com.example.novalinea.presentation.screens.register.components.RegisterBottomBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
	navController: NavController,
	viewModel: RegisterViewModel = hiltViewModel()
) {
	val state = viewModel.state.observeAsState(Success(false)).value
	val focusManager = LocalFocusManager.current

	val stateSteps = remember {
		mutableStateListOf(true, false, false)
	}
	var step = remember {
		mutableStateOf(StepsRegister.MAIN_DATA)
	}

	Scaffold(
		topBar = {
			StepsTopBar(
				stateSteps = stateSteps,
				isShowBack = step.value != StepsRegister.MAIN_DATA,
				onClickBack = {
					when(step.value) {
						StepsRegister.LOGIN -> {
							step.value = StepsRegister.MAIN_DATA
							stateSteps[1] = false
						}
						StepsRegister.PHOTO -> {
							step.value = StepsRegister.LOGIN
							stateSteps[2] = false
						}
					}
				},
				onClickClose = {
					navController.popBackStack()
				}
			)
		},
		bottomBar = {
			RegisterBottomBar(
				enabledRegister = state != Loading
						&& viewModel.name.isValidText()
						&& viewModel.email.isValidText()
						&& viewModel.password.isValidText(),
				onClickLogin = {
					navController.popBackStack()
					navController.navigate(AUTHENTICATION_ROUTE)
				},
				onClickRegister = {
					viewModel.registerByEmail()
				}
			)
		}
	) {
		Column(
			modifier = Modifier.padding(top = 70.dp, start = 40.dp, end = 40.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			when(state) {
				is Loading -> Log.d(TAG, "Loading")
				is Error -> Log.d(TAG, state.message)
				is Success -> {
					if (state.data) {
						LaunchedEffect(state.data) {
							navController.navigate(
								AuthNavRoute.VerifyEmail.route
										+ "?${ARGUMENT_USER_EMAIL_KEY}=${viewModel.email.text}"
							)
						}
					}
				}
			}

			Login(
				viewModel.login.text,
				viewModel.login.error,
				focusManager
			) {
				viewModel.login.text = it
				viewModel.login.validate()
			}
			Spacer(modifier = Modifier.height(10.dp))

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

			if (state is Error) {
				Spacer(modifier = Modifier.height(5.dp))

				when(state.message) {
					EMAIL_IS_USED -> ErrorField(error = INVALID_REGISTER)
					else -> ErrorField(error = ERROR_SERVER)
				}
			}
		}
	}
}