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
import androidx.navigation.NavController
import com.example.projectunion.R
import com.example.projectunion.presentation.navigation.MAIN_ROUTE
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.screens.login.LoginViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(
	navController: NavController,
	viewModel: LoginViewModel = getViewModel<LoginViewModel>()
) {
	val state = viewModel.state.value

	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }

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
				.padding(top = 120.dp),
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
				onValueChange = {value -> email = value},
				placeholder = { Text(stringResource(id = R.string.email_field)) },
				trailingIcon = {
					if (email.isNotBlank()) {
						IconButton(onClick = { email = "" }) {
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
				isError = !isEmailValid
			)

			TextField(
				modifier = Modifier
					.fillMaxWidth()
					.wrapContentHeight(align = Alignment.CenterVertically)
					.height(68.dp)
					.padding(horizontal = 40.dp, vertical = 5.dp),
				value = password,
				onValueChange = {value -> password = value},
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
				isError = !isPasswordValid
			)

			Button(
				onClick = {
					viewModel.loginByEmail(email, password)
					Log.d("AppLog", "${state?.auth}")
					/*auth.signInWithEmailAndPassword(email, password)
						.addOnCompleteListener{
							if (it.isSuccessful) {
								Log.d("AppLog", "Login is successful")
								if (auth.currentUser != null) {
									navController.navigate(MAIN_ROUTE)
								}
							}
							else {
								Log.d("AppLog", "Login is not successful")
							}
						}*/
				},
				modifier = Modifier.padding(top = 7.dp),
				colors = ButtonDefaults.buttonColors(
					backgroundColor = colorResource(id = R.color.app_blue),
					contentColor = Color.White
				),
				shape = RoundedCornerShape(10.dp),
				enabled = isEmailValid && isPasswordValid
			) {
				Text( 
					text="Войти",
					modifier = Modifier
						.padding(horizontal = 5.dp, vertical = 3.dp),
					style = TextStyle(
						fontSize = 16.sp,
						fontWeight = FontWeight.Bold
					)
				)
			}

			TextButton(onClick = { navController.navigate(MainNavRoute.Register.route) }) {
				Text(
					text="Зарегестрироваться",
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