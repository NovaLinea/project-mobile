package com.example.novalinea.presentation.navigation.nav_graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.novalinea.common.Constants.ARGUMENT_USER_DESCRIPTION_KEY
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.ARGUMENT_USER_NAME_KEY
import com.example.novalinea.common.Constants.ARGUMENT_PHOTOS_KEY
import com.example.novalinea.domain.model.Photos
import com.example.novalinea.presentation.components.bottom_sheets.BottomSheet
import com.example.novalinea.presentation.components.bottom_sheets.BottomSheetScreen
import com.example.novalinea.presentation.navigation.*
import com.example.novalinea.presentation.screens.about_app.AboutAppScreen
import com.example.novalinea.presentation.screens.edit_profile.EditProfileScreen
import com.example.novalinea.presentation.screens.profile.ProfileScreen
import com.example.novalinea.presentation.screens.themes.ThemesScreen
import com.example.novalinea.presentation.screens.viewing_photos.ViewingPhotos

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileNavGraph(
	navController: NavHostController,
	router: Router? = null,
	showBottomSheet: ((BottomSheetScreen) -> Unit)? = null
) {
	composable(
		route = BottomNavRoute.Profile.route
				+ "?${ARGUMENT_USER_ID_KEY}={${ARGUMENT_USER_ID_KEY}}",
		arguments = listOf(
			navArgument(
				name = ARGUMENT_USER_ID_KEY
			) {
				type = NavType.StringType
			}
		)
	) {
		PresentNested {
			if (showBottomSheet == null) {
				BottomSheet(
					content = { _, showBottomSheet ->
						ProfileScreen(
							userID = it.arguments?.getString(ARGUMENT_USER_ID_KEY) as String,
							navController = navController,
							showBottomSheet = { bottomSheet ->
								showBottomSheet(bottomSheet)
							}
						)
					}
				)
			}
			else {
				ProfileScreen(
					userID = it.arguments?.getString(ARGUMENT_USER_ID_KEY) as String,
					navController = navController,
					router = router,
					showBottomSheet = { bottomSheet ->
						showBottomSheet(bottomSheet)
					}
				)
			}
		}
	}

	composable(
		route = ProfileNavRoute.ViewingPhoto.route
	) {
		val photos = navController.previousBackStackEntry?.savedStateHandle?.get<Photos>(ARGUMENT_PHOTOS_KEY)
		PresentNested{
			ViewingPhotos(
				photos = photos,
				navController = navController
			)
		}
	}

	composable(
		route = ProfileNavRoute.EditProfile.route
				+ "?$ARGUMENT_USER_ID_KEY={$ARGUMENT_USER_ID_KEY}"
				+ "&${ARGUMENT_USER_NAME_KEY}={${ARGUMENT_USER_NAME_KEY}}"
				+ "&${ARGUMENT_USER_DESCRIPTION_KEY}={${ARGUMENT_USER_DESCRIPTION_KEY}}",
		arguments = listOf(
			navArgument(
				name = ARGUMENT_USER_ID_KEY
			) {
				type = NavType.StringType
			},
			navArgument(
				name = ARGUMENT_USER_NAME_KEY
			) {
				type = NavType.StringType
			},
			navArgument(
				name = ARGUMENT_USER_DESCRIPTION_KEY
			) {
				type = NavType.StringType
			}
		)
	) {
		PresentNested{
			EditProfileScreen(
				id = it.arguments?.getString(ARGUMENT_USER_ID_KEY) as String,
				navController = navController
			)
		}
	}

	composable(
		route = ProfileNavRoute.Themes.route
	) {
		PresentNested{
			ThemesScreen(navController = navController)
		}
	}

	composable(
		route = ProfileNavRoute.AboutApp.route
	) {
		PresentNested{
			AboutAppScreen(navController = navController)
		}
	}
}