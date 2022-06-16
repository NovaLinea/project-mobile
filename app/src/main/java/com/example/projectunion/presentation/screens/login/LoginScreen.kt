package com.example.projectunion.presentation.screens.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projectunion.R
import com.example.projectunion.common.Constants
import com.example.projectunion.common.Constants.INVALID_LOGIN
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.domain.model.Response.*
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.screens.auth.components.Email
import com.example.projectunion.presentation.screens.auth.components.ErrorField
import com.example.projectunion.presentation.screens.auth.components.Password

@Composable
fun LoginScreen(
	navController: NavController,
	viewModel: LoginViewModel = hiltViewModel()
) {
	var focusManager = LocalFocusManager.current

	when (val response = viewModel.state.value) {
		is Loading -> Log.d(Constants.TAG, "Loading")
		is Success -> {
			if (response.data) {
				LaunchedEffect(response.data) {
					navController.navigate(MAIN_ROUTE)
				}
			}
			else {
				Log.d(Constants.TAG, INVALID_LOGIN)
			}
		}
		is Error -> Log.d(Constants.TAG, "Error ${response.message}")
	}

	Scaffold {
		IconButton(
			modifier = Modifier
				.padding(5.dp),
			onClick = {
				navController.navigate(MAIN_ROUTE)
			}
		) {
			Icon(
				imageVector = Icons.Default.Close,
				contentDescription = "Close icon",
				tint = Color.Black
			)
		}

		Column(
			modifier = Modifier
				.padding(top = 100.dp, start = 40.dp, end = 40.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Title()

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
			
			if (viewModel.state.value == Success(false)) {
				ErrorField(error = INVALID_LOGIN)
			}

			ButtonLogin(
				enabled = viewModel.state.value != Loading
						&& viewModel.email.isValidText()
						&& viewModel.password.isValidText()
			) {
				viewModel.loginByEmail()
			}
			Register(navController)
		}
	}
}

@Composable
fun Title() {
	Text(
		text = stringResource(id = R.string.login_screen),
		modifier = Modifier
			.padding(bottom = 30.dp),
		style = TextStyle(
			fontSize = 25.sp,
			fontWeight = FontWeight.Bold
		)
	)
}

@Composable
fun ButtonLogin(
	enabled: Boolean,
	onClicked: () -> Unit
) {
	Button(
		onClick = { onClicked() },
		modifier = Modifier.padding(top = 7.dp),
		colors = ButtonDefaults.buttonColors(
			backgroundColor = colorResource(id = R.color.app_blue),
			contentColor = Color.White
		),
		shape = RoundedCornerShape(10.dp),
		enabled = enabled
	) {
		Text(
			text = Constants.LOGIN,
			modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp),
			style = TextStyle(
				fontSize = 16.sp,
				fontWeight = FontWeight.Bold
			)
		)
	}
}

@Composable
fun Register(navController: NavController) {
	TextButton(onClick = { navController.navigate(MainNavRoute.Register.route) }) {
		Text(
			text=Constants.REGISTER,
			modifier = Modifier.padding(top = 10.dp),
			style = TextStyle(
				fontSize = 16.sp,
				color = Color.Blue
			)
		)
	}
}