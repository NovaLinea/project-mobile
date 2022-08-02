package com.example.novalinea.presentation.screens.profile.components.bottom_sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.novalinea.common.Constants.BUTTON_COMPLAIN
import com.example.novalinea.common.Constants.BUTTON_LOGOUT
import com.example.novalinea.common.Constants.FAVORITES_SCREEN
import com.example.novalinea.common.Constants.SETTINGS_SCREEN
import com.example.novalinea.common.Constants.THEMES_SCREEN
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.presentation.components.bottom_sheets.BottomSheetItem
import com.example.novalinea.presentation.navigation.ProfileNavRoute

@Composable
fun ProfileActions(
	userID: String?,
	navController: NavController? = null,
	onLogout: () -> Unit,
	hideBottomSheet: () -> Unit
) {
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
					hideBottomSheet()
					onLogout()
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

