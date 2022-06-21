package com.example.projectunion.presentation.screens.profile

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectunion.common.Constants.LOGOUT
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.common.Constants.PROFILE_SCREEN
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.top_bar.TopBar
import com.example.projectunion.presentation.navigation.Router

@Composable
fun ProfileScreen(
	userID: String,
	externalRouter: Router,
	viewModel: ProfileViewModel = hiltViewModel()
) {
	when(val state = viewModel.state.observeAsState(Response.Success(false)).value) {
		is Response.Loading -> Log.d(TAG, "Loading")
		is Response.Success -> {
			if (state.data) {
				LaunchedEffect(state.data) {
					externalRouter.navigateTo(MAIN_ROUTE)
				}
			}
		}
		is Response.Error -> Log.d(TAG, state.message)
	}

	Scaffold(
		topBar = { TopBar(userID) },
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(top = 170.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			TextButton(
				onClick = {
					viewModel.logout()
				}
			) {
				Text(
					text = LOGOUT,
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