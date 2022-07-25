package com.example.novalinea.presentation.navigation.nav_graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.novalinea.common.Constants.ARGUMENT_CHAT_DATA
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.MESSAGES_ROUTE
import com.example.novalinea.domain.model.ChatOpen
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
					+ "?${ARGUMENT_USER_ID_KEY}={${ARGUMENT_USER_ID_KEY}}",
			arguments = listOf(
				navArgument(
					name = ARGUMENT_USER_ID_KEY
				) {
					type = NavType.StringType
				}
			)
		) {
			val chat = navController.previousBackStackEntry?.savedStateHandle?.get<ChatOpen>(ARGUMENT_CHAT_DATA)
			chat?.let {
				PresentNested {
					ChatScreen(
						chat = it,
						navController = navController
					)
				}
			}
		}
	}
}