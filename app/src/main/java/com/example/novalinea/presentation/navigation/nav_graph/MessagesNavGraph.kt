package com.example.novalinea.presentation.navigation.nav_graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.ARGUMENT_USER_NAME_KEY
import com.example.novalinea.common.Constants.MESSAGES_ROUTE
import com.example.novalinea.presentation.navigation.MessagesNavRoute
import com.example.novalinea.presentation.navigation.PresentNested
import com.example.novalinea.presentation.screens.chat.ChatScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.messagesNavGraph(
	navController: NavHostController
) {
	navigation(
		startDestination = MessagesNavRoute.Chat.route,
		route = MESSAGES_ROUTE
	) {
		composable(
			route = MessagesNavRoute.Chat.route
					+ "?${ARGUMENT_USER_ID_KEY}={${ARGUMENT_USER_ID_KEY}}"
					+ "&${ARGUMENT_USER_NAME_KEY}={${ARGUMENT_USER_NAME_KEY}}",
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
			)
		) {
			PresentNested {
				ChatScreen(
					userId = it.arguments?.getString(ARGUMENT_USER_ID_KEY) as String,
					userName = it.arguments?.getString(ARGUMENT_USER_NAME_KEY) as String,
					navController = navController
				)
			}
		}
	}
}