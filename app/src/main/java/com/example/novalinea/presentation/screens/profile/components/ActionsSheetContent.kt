package com.example.novalinea.presentation.screens.profile.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.example.novalinea.presentation.components.loader.Loader
import com.example.novalinea.presentation.navigation.ProfileNavRoute
import com.example.novalinea.presentation.navigation.Router

@Composable
fun ActionsSheetContent(
	userID: String?,
	router: Router?,
	navController: NavController,
	hideBottomSheet: () -> Unit,
	viewModel: ActionsSheetViewModel = hiltViewModel()
) {
	if (userID == USER.id)
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
						navController.navigate(MAIN_ROUTE)
					USER = UserProfile()
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
			ActionItem(
				title = SETTINGS_SCREEN,
				icon = Icons.Default.Settings,
				onClick = {
					hideBottomSheet()
				}
			)

			ActionItem(
				title = FAVORITES_SCREEN,
				icon = Icons.Default.Bookmark,
				onClick = {
					hideBottomSheet()
				}
			)

			ActionItem(
				title = THEMES_SCREEN,
				icon = Icons.Default.WbSunny,
				onClick = {
					hideBottomSheet()
					navController.navigate(ProfileNavRoute.Themes.route)
				}
			)

			ActionItem(
				title = BUTTON_LOGOUT,
				icon = Icons.Default.Logout,
				onClick = {
					viewModel.logout()
				}
			)
		}
		else {
			ActionItem(
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

@Composable
fun ActionItem(
	title: String,
	icon: ImageVector,
	onClick: () -> Unit
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(50.dp)
			.background(Color.White)
			.clickable { onClick() },
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Start
	) {
		Box(
			modifier = Modifier
				.padding(start = 20.dp)
				.size(20.dp),
			contentAlignment = Alignment.Center
		) {
			Icon(
				modifier = Modifier.fillMaxSize(),
				imageVector = icon,
				contentDescription = null
			)
		}

		Text(
			text = title,
			modifier = Modifier.padding(start = 15.dp),
			style = TextStyle(
				fontSize = 17.sp,
				fontWeight = FontWeight.W500,
				color = Color.Black,
			),
		)
	}
}