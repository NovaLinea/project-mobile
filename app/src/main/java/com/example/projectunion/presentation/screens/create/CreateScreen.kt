package com.example.projectunion.presentation.screens.create

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projectunion.common.Constants.CREATE_PROJECT
import com.example.projectunion.common.Constants.CREATE_SCREEN
import com.example.projectunion.common.Constants.DESCRIPTION_PROJECT_PLACEHOLDER
import com.example.projectunion.common.Constants.TITLE_PROJECT_PLACEHOLDER
import com.example.projectunion.presentation.components.button_action.ButtonAction
import com.example.projectunion.presentation.components.top_bar.TopBar


@Composable
fun CreateScreen(
	typeProject: String,
	navController: NavController,
	viewModel: CreateViewModel = hiltViewModel()
) {
	Scaffold(
		topBar = { TopBar(CREATE_SCREEN) },
	) {
		Column(
			modifier = Modifier
				.padding(horizontal = 15.dp, vertical = 10.dp)
		) {
			TextFieldPlaceholder(
				title = viewModel.title.text,
				placeholder = TITLE_PROJECT_PLACEHOLDER,
				isPlaceholderVisible = viewModel.title.text.isEmpty(),
				onChangeTitle = {
					viewModel.title.text = it
				},
				textStyle = MaterialTheme.typography.h5,
				singleLine = true
			)
			
			Spacer(modifier = Modifier.height(7.dp))
			
			TextFieldPlaceholder(
				title = viewModel.description.text,
				placeholder = DESCRIPTION_PROJECT_PLACEHOLDER,
				isPlaceholderVisible = viewModel.description.text.isEmpty(),
				onChangeTitle = {
					viewModel.description.text = it
				},
				textStyle = MaterialTheme.typography.body1,
				singleLine = false
			)

			Spacer(modifier = Modifier.height(7.dp))
			
			ButtonAction(title = CREATE_PROJECT, enabled = true) {
				viewModel.createProject()
			}
		}
	}
}

@Composable
fun TextFieldPlaceholder(
	title: String,
	placeholder: String,
	isPlaceholderVisible: Boolean,
	onChangeTitle: (String) -> Unit,
	textStyle: TextStyle,
	singleLine: Boolean
) {
	Box() {
		BasicTextField(
			value = title,
			onValueChange = { value -> onChangeTitle(value) },
			singleLine = singleLine,
			modifier = Modifier
				.fillMaxWidth(),
			textStyle = textStyle
		)

		if (isPlaceholderVisible) {
			Text(
				text = placeholder,
				color = Color.DarkGray,
				style = textStyle
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun prevCreateScreen() {
	CreateScreen("sale", rememberNavController())
}