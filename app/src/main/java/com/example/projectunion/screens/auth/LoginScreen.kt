package com.example.projectunion.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectunion.R
import com.example.projectunion.navigation.ProjectNavRoute

@Composable
fun LoginScreen(navController: NavController) {
	var email by remember {
		mutableStateOf("")
	}
	var password by remember {
		mutableStateOf("")
	}

	Scaffold {
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
					.height(65.dp)
					.padding(horizontal = 40.dp, vertical = 5.dp),
				value = email,
				onValueChange = {value -> email = value},
				placeholder = { Text(stringResource(id = R.string.email_field)) },
				singleLine = true,
				textStyle = TextStyle(fontSize = 16.sp),
				shape = RoundedCornerShape(10.dp),
				colors = TextFieldDefaults.textFieldColors(
					textColor = Color.Gray,
					disabledTextColor = Color.Transparent,
					backgroundColor = colorResource(id = R.color.app_background),
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent,
					disabledIndicatorColor = Color.Transparent
				)
			)

			TextField(
				modifier = Modifier
					.fillMaxWidth()
					.wrapContentHeight(align = Alignment.CenterVertically)
					.height(65.dp)
					.padding(horizontal = 40.dp, vertical = 5.dp),
				value = password,
				onValueChange = {value -> password = value},
				placeholder = { Text(stringResource(id = R.string.password_field)) },
				singleLine = true,
				textStyle = TextStyle(fontSize = 16.sp),
				shape = RoundedCornerShape(10.dp),
				colors = TextFieldDefaults.textFieldColors(
					textColor = Color.Gray,
					disabledTextColor = Color.Transparent,
					backgroundColor = colorResource(id = R.color.app_background),
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent,
					disabledIndicatorColor = Color.Transparent
				)
			)

			Button(
				onClick = {

				},
				modifier = Modifier
					.padding(top = 7.dp),
				colors = ButtonDefaults.buttonColors(
					backgroundColor = colorResource(id = R.color.app_blue),
					contentColor = Color.White
				),
				shape = RoundedCornerShape(10.dp)
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

			Text(
				text="Зарегестрироваться",
				modifier = Modifier
					.padding(top = 10.dp)
					.clickable {
						navController.navigate(ProjectNavRoute.Register.route)
					},
				style = TextStyle(
					fontSize = 16.sp,
					color = Color.Blue
				)
			)
		}
	}
}