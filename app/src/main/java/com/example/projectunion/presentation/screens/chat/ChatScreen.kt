package com.example.projectunion.presentation.screens.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectunion.common.Constants.MESSAGE_FIELD
import com.example.projectunion.presentation.components.icon_button.IconButtonAction
import com.example.projectunion.presentation.screens.chat.components.ChatTopBar

@Composable
fun ChatScreen(
	//viewModel: ChatViewModel = hiltViewModel()
) {
	Scaffold(
		topBar = {
			ChatTopBar(
				onClickBack = {}
			)
		},
		bottomBar = {
			MessageField(
				value = "",
				onValueChange = {

				},
				placeholder = MESSAGE_FIELD,
				isPlaceholderVisible = true,
				onSend = {

				}
			)
		}
	) { innerPadding ->
		Box(
			modifier = Modifier.padding(innerPadding)
		) {
			Column() {

			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun prevChatScreen() {
	ChatScreen()
}

@Composable
fun MessageField(
	value: String,
	onValueChange: (String) -> Unit,
	placeholder: String,
	isPlaceholderVisible: Boolean,
	onSend: () -> Unit
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 10.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Box(
			modifier = Modifier.fillMaxWidth(0.9f),
			contentAlignment = Alignment.CenterStart
		) {
			BasicTextField(
				value = value,
				onValueChange = { value -> onValueChange(value) },
				singleLine = true,
				modifier = Modifier
					.height(50.dp)
					.fillMaxWidth(),
				textStyle = MaterialTheme.typography.body1,
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Done
				),
				keyboardActions = KeyboardActions(
					onDone = { onSend() }
				),
			)

			if (isPlaceholderVisible) {
				Text(
					text = placeholder,
					color = Color.DarkGray,
					style = MaterialTheme.typography.body1
				)
			}
		}
		
		IconButtonAction(
			icon = Icons.Default.Send,
			tint = Color.DarkGray,
			onClick = { onSend() }
		)
	}
}