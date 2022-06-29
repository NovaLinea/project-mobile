package com.example.projectunion.presentation.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projectunion.R
import com.example.projectunion.common.Constants.MESSAGE_FIELD
import com.example.projectunion.presentation.screens.chat.components.ChatTopBar
import com.example.projectunion.presentation.screens.chat.components.MessageField
import com.example.projectunion.presentation.screens.chat.components.MessageItem

@Composable
fun ChatScreen(
	navController: NavController,
	viewModel: ChatViewModel = hiltViewModel()
) {
	Scaffold(
		topBar = {
			ChatTopBar(
				onClickBack = { navController.popBackStack() }
			)
		},
		bottomBar = {
			MessageField(
				value = viewModel.message.text,
				onValueChange = {
					viewModel.message.text = it
				},
				placeholder = MESSAGE_FIELD,
				isPlaceholderVisible = viewModel.message.text.isEmpty(),
				onSend = {
					if (viewModel.message.text.isNotEmpty())
						viewModel.sendMessage()
				}
			)
		}
	) { innerPadding ->
		Box(
			modifier = Modifier
				.padding(innerPadding)
		) {
			LazyColumn(
				modifier = Modifier
					.fillMaxSize()
					.background(colorResource(id = R.color.app_background))
			) {
				item() {
					Spacer(modifier = Modifier.height(10.dp))
					MessageItem(
						message = "Hello",
						time = "21:45",
						locationArrangement = Arrangement.Start
					)
					Spacer(modifier = Modifier.height(10.dp))
					MessageItem(
						message = "Hi! How are you?",
						time = "21:51",
						locationArrangement = Arrangement.End
					)
					Spacer(modifier = Modifier.height(10.dp))
					MessageItem(
						message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris non libero in mi vehicula facilisis. Sed faucibus nisl eget orci dapibus euismod. Curabitur quis tellus sollicitudin nunc luctus fermentum. Aenean iaculis turpis vel dictum malesuada. Praesent nisi ligula, scelerisque in ex ut, malesuada efficitur ante.",
						time = "21:54",
						locationArrangement = Arrangement.End
					)

					Spacer(modifier = Modifier.height(10.dp))
					MessageItem(
						message = "Lorem ipsum dolor sit amet",
						time = "21:57",
						locationArrangement = Arrangement.Start
					)
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun prevChatScreen() {
	ChatScreen(rememberNavController())
}