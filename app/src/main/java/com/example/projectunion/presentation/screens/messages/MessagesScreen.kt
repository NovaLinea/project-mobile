package com.example.projectunion.presentation.screens.messages

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectunion.common.Constants.MESSAGES_SCREEN
import com.example.projectunion.common.Constants.TITLE_NO_DIALOGS
import com.example.projectunion.presentation.components.button_action.ButtonActionText
import com.example.projectunion.presentation.components.top_bar.TopBar
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.navigation.Router

@Composable
fun MessagesScreen(
	externalRouter: Router,
	viewModel: MessagesViewModel = hiltViewModel()
) {
	Scaffold(
		topBar = { TopBar(MESSAGES_SCREEN) },
	) {
		ButtonActionText(title = "Open chat") {
			externalRouter.navigateTo(MainNavRoute.Chat.route)
		}
		Column(
			modifier = Modifier.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				modifier = Modifier.padding(top = 170.dp),
				text = TITLE_NO_DIALOGS,
				style = MaterialTheme.typography.body2
			)
		}
	}
}