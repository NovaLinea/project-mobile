package com.example.novalinea.presentation.screens.create.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.novalinea.R
import com.example.novalinea.common.Constants.DESCRIPTION_PROJECT_PLACEHOLDER
import com.example.novalinea.common.Constants.TITLE_PROJECT_PLACEHOLDER
import com.example.novalinea.presentation.screens.create.components.create_text_field.CreateTextField

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainInformationProject(
	title: String,
	onTitleChange: (String) -> Unit,
	description: String,
	onDescriptionChange: (String) -> Unit,
	enabled: Boolean,
	nextStep: () -> Unit
) {
	Scaffold(
		bottomBar = {
			Box(
				modifier = Modifier
					.padding(end = 10.dp, bottom = 10.dp)
					.fillMaxWidth(),
				contentAlignment = Alignment.CenterEnd
			) {
				Button(
					modifier = Modifier.size(50.dp),
					onClick = { nextStep() },
					colors = ButtonDefaults.buttonColors(
						backgroundColor = colorResource(id = R.color.app_light_blue),
						contentColor = Color.White
					),
					shape = CircleShape,
					enabled = enabled
				) {
					Icon(
						imageVector = Icons.Default.ArrowForward,
						contentDescription = null
					)
				}
			}
		}
	) {
		Column(
			modifier = Modifier.verticalScroll(rememberScrollState())
		) {
			Column(
				modifier = Modifier
					.padding(start = 15.dp, end = 15.dp, bottom = 50.dp)
			) {
				CreateTextField(
					value = title,
					placeholder = TITLE_PROJECT_PLACEHOLDER,
					isPlaceholderVisible = title.isEmpty(),
					onValueChange = onTitleChange,
					textStyle = MaterialTheme.typography.h3
				)

				Spacer(modifier = Modifier.height(10.dp))

				CreateTextField(
					value = description,
					placeholder = DESCRIPTION_PROJECT_PLACEHOLDER,
					isPlaceholderVisible = description.isEmpty(),
					onValueChange = onDescriptionChange,
					textStyle = MaterialTheme.typography.body1
				)
			}

		}
	}
}