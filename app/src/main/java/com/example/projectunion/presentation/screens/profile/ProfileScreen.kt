package com.example.projectunion.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.projectunion.R
import com.example.projectunion.common.Constants
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.navigation.Router
import com.example.projectunion.presentation.screens.profile.ProfileViewModel

@Composable
fun ProfileScreen(
	externalRouter: Router,
	viewModel: ProfileViewModel = hiltViewModel()
) {
	when (val response = viewModel.state.value) {
		is Response.Loading -> Log.d(Constants.TAG, "Loading")
		is Response.Success -> {
			if (response.data) {
				LaunchedEffect(response.data) {
					externalRouter.navigateTo(MAIN_ROUTE)
				}
			}
			else {
				Log.d(Constants.TAG, "Error logout")
			}
		}
		is Response.Error -> Log.d(Constants.TAG, "Error ${response.message}")
	}

	Scaffold(
		topBar = {
			TopAppBar(
				backgroundColor = Color.White,
				elevation = 0.dp
			) {
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.Center
				) {
					Text(
						text = stringResource(id = R.string.profile_screen),
						style = TextStyle(
							color = Color.Black,
							fontWeight = FontWeight.W600,
							fontSize = 18.sp
						)
					)
				}
			}
		},
	) {
		Column(
			modifier = Modifier.fillMaxSize().padding(top = 170.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			TextButton(
				onClick = {
					viewModel.logout()
				}
			) {
				Text(
					text= Constants.LOGOUT,
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