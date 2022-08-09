package com.example.novalinea.presentation.screens.create.components.steps

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.novalinea.common.Constants.DESCRIPTION_PROJECT_PLACEHOLDER
import com.example.novalinea.common.Constants.TITLE_PROJECT_PLACEHOLDER
import com.example.novalinea.presentation.components.button_action.ButtonNextStep
import com.example.novalinea.presentation.screens.create.components.create_text_field.CreateTextField

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainInformationProject(
	title: String,
	onTitleChange: (String) -> Unit,
	description: String,
	onDescriptionChange: (String) -> Unit,
	enabled: Boolean,
	onClickNext: () -> Unit
) {
	Scaffold(
		bottomBar = {
			Box(
				modifier = Modifier
					.padding(end = 15.dp, bottom = 15.dp)
					.fillMaxWidth(),
				contentAlignment = Alignment.CenterEnd
			) {
				ButtonNextStep(
					enabled = enabled,
					onClickNext = onClickNext
				)
			}
		}
	) {
		Column(
			modifier = Modifier.verticalScroll(rememberScrollState())
		) {
			Column(
				modifier = Modifier
					.padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 65.dp)
			) {
				CreateTextField(
					value = title,
					placeholder = TITLE_PROJECT_PLACEHOLDER,
					onValueChange = onTitleChange,
					textStyle = MaterialTheme.typography.h3
				)

				Spacer(modifier = Modifier.height(10.dp))

				CreateTextField(
					value = description,
					placeholder = DESCRIPTION_PROJECT_PLACEHOLDER,
					onValueChange = onDescriptionChange,
					textStyle = MaterialTheme.typography.body1
				)
			}

		}
	}
}