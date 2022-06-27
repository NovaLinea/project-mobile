package com.example.projectunion.presentation.screens.additionally

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectunion.common.Constants
import com.example.projectunion.common.Constants.ADDITIONALLY_SCREEN
import com.example.projectunion.common.Constants.LOGOUT
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.common.Constants.USER
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.text_button_action.TextButtonAction
import com.example.projectunion.presentation.components.top_bar.TopBar
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.navigation.Router
import com.example.projectunion.presentation.screens.additionally.components.AdditionallyItem
import com.example.projectunion.presentation.screens.additionally.components.AdditionallyModel
import com.example.projectunion.presentation.screens.additionally.components.ProfileUser

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

	val items = listOf(
		AdditionallyModel.Favorites,
		//AdditionallyModel.Settings,
		//AdditionallyModel.Notifications,
		AdditionallyModel.Theme,
		AdditionallyModel.AboutApp
	)

	Scaffold(
		topBar = { TopBar(title = ADDITIONALLY_SCREEN) },
	) {
		Column(
			modifier = Modifier.fillMaxWidth(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			ProfileUser(
				name = USER.name,
				photo  = USER.photo,
				onClick = {
					USER.id?.let { id ->
						openProfile(externalRouter, id)
					}
				}
			)
			
			Spacer(modifier = Modifier.height(10.dp))

			items.forEach { item ->
				AdditionallyItem(title = item.title, icon = item.icon) {
					externalRouter.navigateTo(item.screen_route)
				}
			}

			TextButtonAction(
				title = LOGOUT,
				color = Color.Red
			) {
				viewModel.logout()
			}
		}
	}
}

fun openProfile(
	externalRouter: Router,
	id: String
) {
	externalRouter.navigateTo(
		MainNavRoute.Profile.route + "?${Constants.ARGUMENT_PROFILE_KEY}=${id}"
	)
}