package com.example.novalinea.presentation.screens.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.novalinea.common.Constants.ERROR_INVALID_USER
import com.example.novalinea.common.Constants.ERROR_FIREBASE_INVALID_PASSWORD
import com.example.novalinea.common.Constants.ERROR_INVALID_PASSWORD
import com.example.novalinea.common.Constants.MAIN_ROUTE
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.common.Constants.TITLE_LOGIN_ACCOUNT
import com.example.novalinea.common.Constants.ERROR_FIREBASE_USER_NOT_FOUND
import com.example.novalinea.domain.model.Response.*
import com.example.novalinea.presentation.components.email_field.Email
import com.example.novalinea.presentation.components.password_field.Password
import com.example.novalinea.presentation.components.error_field.ErrorField
import com.example.novalinea.presentation.navigation.AuthNavRoute
import com.example.novalinea.presentation.screens.login.components.LoginBottomBar
import com.example.novalinea.presentation.screens.login.components.LoginTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
	navController: NavController,
	viewModel: LoginViewModel = hiltViewModel()
) {
	val state = viewModel.state.observeAsState(Success(false)).value
	val focusManager = LocalFocusManager.current

	Scaffold(
		topBar = {
			LoginTopBar() {
				navController.popBackStack()
			}
		},
		bottomBar = {
			LoginBottomBar(
				enabled = state != Loading
						&& viewModel.email.isValidText()
						&& viewModel.password.isValidText(),
				onClickRegister = {
					navController.popBackStack()
					navController.navigate(AuthNavRoute.Register.route)
				},
				onClickLogin = {
					viewModel.loginByEmail()
				}
			)
		}
	) {
		when(state) {
			is Loading -> Log.d(TAG, "Loading")
			is Error -> Log.d(TAG, state.message)
			is Success -> {
				if (state.data) {
					viewModel.getDataUser()
					LaunchedEffect(state.data) {
						navController.navigate(MAIN_ROUTE)
					}
				}
			}
		}

		Column(
			modifier = Modifier
				.fillMaxWidth()
				.verticalScroll(rememberScrollState()),
		) {
			Spacer(modifier = Modifier.height(50.dp))
			Box(
				contentAlignment = Alignment.CenterStart
			) {
				Text(
					modifier = Modifier.padding(start = 30.dp),
					text = TITLE_LOGIN_ACCOUNT,
					style = MaterialTheme.typography.h5
				)
			}

			Spacer(modifier = Modifier.height(20.dp))
			Column(
				modifier = Modifier.padding(horizontal = 30.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {

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
						ERROR_FIREBASE_USER_NOT_FOUND -> ErrorField(error = ERROR_INVALID_USER)
						ERROR_FIREBASE_INVALID_PASSWORD -> ErrorField(error = ERROR_INVALID_PASSWORD)
						ERROR_VERIFY_EMAIL -> ErrorField(error = ERROR_VERIFY_EMAIL)
						else -> ErrorField(error = ERROR_SERVER)
					}
				}
			}
		}
	}
}