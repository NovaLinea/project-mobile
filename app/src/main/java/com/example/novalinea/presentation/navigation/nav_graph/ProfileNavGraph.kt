package com.example.novalinea.presentation.navigation.nav_graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.novalinea.common.Constants.ARGUMENT_USER_DESCRIPTION_KEY
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.ARGUMENT_USER_NAME_KEY
import com.example.novalinea.common.Constants.PROFILE_ROUTE
import com.example.novalinea.presentation.navigation.PresentNested
import com.example.novalinea.presentation.navigation.ProfileNavRoute
import com.example.novalinea.presentation.screens.about_app.AboutAppScreen
import com.example.novalinea.presentation.screens.edit_profile.EditProfileScreen
import com.example.novalinea.presentation.screens.profile.ProfileScreen
import com.example.novalinea.presentation.screens.themes.ThemesScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileNavGraph(
	navController: NavHostController
) {
	navigation(
		startDestination = ProfileNavRoute.Profile.route,
		route = PROFILE_ROUTE
	) {
		composable(
			route = ProfileNavRoute.Profile.route
					+ "?${ARGUMENT_USER_ID_KEY}={${ARGUMENT_USER_ID_KEY}}",
			arguments = listOf(
				navArgument(
					name = ARGUMENT_USER_ID_KEY
				) {
					type = NavType.StringType
					defaultValue = "-1"
				}
			)
		) {
			PresentNested{
				ProfileScreen(
					userID = it.arguments?.getString(ARGUMENT_USER_ID_KEY) as String,
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
					defaultValue = "-1"
				},
				navArgument(
					name = ARGUMENT_USER_NAME_KEY
				) {
					type = NavType.StringType
					defaultValue = "-1"
				},
				navArgument(
					name = ARGUMENT_USER_DESCRIPTION_KEY
				) {
					type = NavType.StringType
					defaultValue = "-1"
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