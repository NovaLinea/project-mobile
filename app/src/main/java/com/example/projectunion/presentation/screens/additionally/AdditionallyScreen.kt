package com.example.projectunion.presentation.screens.additionally

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectunion.common.Constants.ADDITIONALLY_SCREEN
import com.example.projectunion.common.Constants.LOGOUT
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.text_button_action.TextButtonAction
import com.example.projectunion.presentation.components.top_bar.TopBar
import com.example.projectunion.presentation.navigation.Router

@Composable
fun AdditionallyScreen(
	externalRouter: Router,
	viewModel: AdditionallyViewModel = hiltViewModel(),
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
		topBar = { TopBar(title = ADDITIONALLY_SCREEN) },
	) {
		Column(
			modifier = Modifier.fillMaxWidth(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			TextButtonAction(
				title = LOGOUT,
				color = Color.Red
			) {
				viewModel.logout()
			}
		}
	}
}