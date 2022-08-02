package com.example.novalinea.presentation.screens.profile.components.bottom_sheets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.novalinea.common.Constants.BUTTON_COMPLAIN
import com.example.novalinea.common.Constants.BUTTON_LOGOUT
import com.example.novalinea.common.Constants.FAVORITES_SCREEN
import com.example.novalinea.common.Constants.MAIN_ROUTE
import com.example.novalinea.common.Constants.SETTINGS_SCREEN
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.common.Constants.THEMES_SCREEN
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.UserProfile
import com.example.novalinea.presentation.components.bottom_sheets.BottomSheetItem
import com.example.novalinea.presentation.components.loader.Loader
import com.example.novalinea.presentation.navigation.ProfileNavRoute
import com.example.novalinea.presentation.navigation.Router

@Composable
fun ProfileActions(
	userID: String?,
	router: Router? = null,
	navController: NavController? = null,
	hideBottomSheet: () -> Unit,
	viewModel: BottomSheetViewModel = hiltViewModel()
) {
	if (userID == USER.id) {
		when(val stateLogout = viewModel.stateLogout.observeAsState(Response.Success(false)).value) {
			is Response.Loading -> Loader()
			is Response.Error -> {
				Log.d(TAG, stateLogout.message)
				//scope.launch {
				//	scaffoldState.snackbarHostState.showSnackbar(Constants.ERROR_BY_LOGOUT)
				//}
			}
			is Response.Success -> {
				if (stateLogout.data) {
					LaunchedEffect(stateLogout.data) {
						if (router != null)
							router.routeTo(MAIN_ROUTE)
						else
							navController?.navigate(MAIN_ROUTE)
						USER = UserProfile()
					}
				}
			}
		}
	}

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.White)
	) {
		Row(
			modifier = Modifier
				.padding(top = 10.dp)
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.Center
		) {
			Card(
				modifier = Modifier
					.height(5.dp)
					.width(50.dp),
				backgroundColor = Color.LightGray,
				elevation = 0.dp,
				shape = RoundedCornerShape(10.dp)
			) {}
		}
		Spacer(modifier = Modifier.height(10.dp))

		if (userID == USER.id) {
			BottomSheetItem(
				title = SETTINGS_SCREEN,
				icon = Icons.Default.Settings,
				onClick = {
					hideBottomSheet()
				}
			)

			BottomSheetItem(
				title = FAVORITES_SCREEN,
				icon = Icons.Default.Bookmark,
				onClick = {
					hideBottomSheet()
				}
			)

			BottomSheetItem(
				title = THEMES_SCREEN,
				icon = Icons.Default.WbSunny,
				onClick = {
					hideBottomSheet()
					navController?.navigate(ProfileNavRoute.Themes.route)
				}
			)

			BottomSheetItem(
				title = BUTTON_LOGOUT,
				icon = Icons.Default.Logout,
				onClick = {
					viewModel.logout()
				}
			)
		}
		else {
			BottomSheetItem(
				title = BUTTON_COMPLAIN,
				icon = Icons.Default.Report,
				onClick = {
					hideBottomSheet()
				}
			)
		}

		Spacer(modifier = Modifier.height(15.dp))
	}
}

