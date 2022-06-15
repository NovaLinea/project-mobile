package com.example.projectunion.presentation.screens.register

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projectunion.R
import com.example.projectunion.common.Constants
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.domain.model.Response.*
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.screens.login.LoginViewModel

@Composable
fun LoginScreen(
	navController: NavController,
	viewModel: LoginViewModel = hiltViewModel()
) {
	val email by viewModel.email.collectAsState()
	val password by viewModel.password.collectAsState()

	var passwordVisibility by remember { mutableStateOf(false) }
	val iconVisibility = if (passwordVisibility)
		Icons.Default.Visibility
	else
		Icons.Default.VisibilityOff

	val minCharPassword = 6
	var focusManager = LocalFocusManager.current

	val isEmailValid by derivedStateOf {
		if (email.isNotEmpty())
			Patterns.EMAIL_ADDRESS.matcher(email).matches()
		else
			false
	}

	val isPasswordValid by derivedStateOf {
		if (password.isNotEmpty())
			password.length >= minCharPassword
		else
			false
	}

	when (val response = viewModel.state.value) {
		is Loading -> Log.d(Constants.TAG, "Loading")
		is Success -> {
			if (response.data) {
				LaunchedEffect(response.data) {
					navController.navigate(MAIN_ROUTE)
				}
			}
			else {
				Log.d(Constants.TAG, "Error login")
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
				.padding(top = 100.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = stringResource(id = R.string.login_screen),
				modifier = Modifier
					.padding(bottom = 30.dp),
				style = TextStyle(
					fontSize = 25.sp,
					fontWeight = FontWeight.Bold
				)
			)

			TextField(
				modifier = Modifier
					.fillMaxWidth()
					.wrapContentHeight(align = Alignment.CenterVertically)
					.height(68.dp)
					.padding(horizontal = 40.dp, vertical = 5.dp),
				value = email,
				onValueChange = {value -> viewModel.email.value = value},
				placeholder = { Text(stringResource(id = R.string.email_field)) },
				trailingIcon = {
					if (email.isNotBlank()) {
						IconButton(
							onClick = { viewModel.email.value = "" }
						) {
							Icon(
								imageVector = Icons.Default.Clear,
								contentDescription = "Clear icon"
							)
						}
					}
				},
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Email,
					imeAction = ImeAction.Next
				),
				keyboardActions = KeyboardActions(
					onNext = { focusManager.moveFocus(FocusDirection.Down) }
				),
				singleLine = true,
				textStyle = TextStyle(
					fontSize = 18.sp,
					color = Color.Black
				),
				shape = RoundedCornerShape(10.dp),
				colors = TextFieldDefaults.textFieldColors(
					textColor = Color.Gray,
					disabledTextColor = Color.Transparent,
					backgroundColor = colorResource(id = R.color.app_background),
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent,
					disabledIndicatorColor = Color.Transparent
				),
				//isError = !isEmailValid
			)

			TextField(
				modifier = Modifier
					.fillMaxWidth()
					.wrapContentHeight(align = Alignment.CenterVertically)
					.height(68.dp)
					.padding(horizontal = 40.dp, vertical = 5.dp),
				value = password,
				onValueChange = {value -> viewModel.password.value = value},
				placeholder = { Text(stringResource(id = R.string.password_field)) },
				trailingIcon = {
					IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
						Icon(
							imageVector = iconVisibility,
							contentDescription = "Visibility icon"
						)
					}
				},
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Password,
					imeAction = ImeAction.Done
				),
				keyboardActions = KeyboardActions(
					onDone = { focusManager.clearFocus() }
				),
				visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
				singleLine = true,
				textStyle = TextStyle(
					fontSize = 18.sp,
					color = Color.Black
				),
				shape = RoundedCornerShape(10.dp),
				colors = TextFieldDefaults.textFieldColors(
					textColor = Color.Gray,
					disabledTextColor = Color.Transparent,
					backgroundColor = colorResource(id = R.color.app_background),
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent,
					disabledIndicatorColor = Color.Transparent
				),
				//isError = !isPasswordValid
			)

			Button(
				onClick = {
					viewModel.loginByEmail()
				},
				modifier = Modifier.padding(top = 7.dp),
				colors = ButtonDefaults.buttonColors(
					backgroundColor = colorResource(id = R.color.app_blue),
					contentColor = Color.White
				),
				shape = RoundedCornerShape(10.dp),
				enabled = isEmailValid && isPasswordValid && viewModel.state.value != Loading
			) {
				if (viewModel.state.value == Loading) {
					CircularProgressIndicator(
						modifier = Modifier
							.size(30.dp),
						color = Color.White
					)
				}
				else {
					Text(
						text = Constants.LOGIN,
						modifier = Modifier
							.padding(horizontal = 5.dp, vertical = 3.dp),
						style = TextStyle(
							fontSize = 16.sp,
							fontWeight = FontWeight.Bold
						)
					)
				}
			}

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
	}
}