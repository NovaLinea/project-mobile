package com.example.novalinea.presentation.screens.veriftyEmail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.novalinea.R
import com.example.novalinea.common.Constants.AUTHENTICATION_ROUTE
import com.example.novalinea.common.Constants.BUTTON_LOGIN_TO_ACCOUNT
import com.example.novalinea.common.Constants.MAIN_ROUTE
import com.example.novalinea.common.Constants.TEXT_VERIFY_EMAIL
import com.example.novalinea.common.Constants.VERIFY_EMAIL_SCREEN
import com.example.novalinea.presentation.components.button_action.ButtonActionText
import com.example.novalinea.presentation.screens.veriftyEmail.components.VerifyTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VerifyEmailScreen(
	email: String,
	navController: NavController
) {
	Scaffold(
		topBar = {
			VerifyTopBar() {
				navController.navigate(MAIN_ROUTE)
			}
		}
	) {
		Column(
			modifier = Modifier
				.padding(top = 120.dp, start = 40.dp, end = 40.dp)
				.fillMaxWidth(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Icon(
				modifier = Modifier.size(64.dp),
				painter = painterResource(id = R.drawable.ic_check_circle_fill),
				contentDescription = null,
				tint = Color.DarkGray
			)

			Spacer(modifier = Modifier.height(5.dp))
			Text(
				text = VERIFY_EMAIL_SCREEN,
				style = MaterialTheme.typography.h5
			)

			Spacer(modifier = Modifier.height(15.dp))
			Text(
				modifier = Modifier.padding(horizontal = 10.dp),
				text = "$TEXT_VERIFY_EMAIL\n$email",
				style = MaterialTheme.typography.body2,
				textAlign = TextAlign.Center
			)

			Spacer(modifier = Modifier.height(15.dp))
			ButtonActionText(
				BUTTON_LOGIN_TO_ACCOUNT
			) {
				navController.navigate(AUTHENTICATION_ROUTE)
			}
		}
	}
}