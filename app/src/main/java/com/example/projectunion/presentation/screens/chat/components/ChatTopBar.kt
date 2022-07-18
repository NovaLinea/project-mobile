package com.example.projectunion.presentation.screens.chat.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectunion.R
import com.example.projectunion.presentation.components.icon_button.IconButtonAction
import com.example.projectunion.presentation.components.image_painter.ImagePainter

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChatTopBar(
	title: String,
	photo: String?,
	onClickBack: () -> Unit,
	onClickUser: () -> Unit
) {
	Scaffold(
		modifier = Modifier
			.height(55.dp)
			.fillMaxWidth()
	) {
		Box(
			modifier = Modifier
				.padding(start = 5.dp)
				.fillMaxSize(),
			contentAlignment = Alignment.CenterStart
		) {
			IconButtonAction(Icons.Default.ArrowBack) {
				onClickBack()
			}
		}

		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			Text(
				modifier = Modifier.padding(start = 10.dp),
				text = title,
				style = MaterialTheme.typography.subtitle2
			)
		}

		Box(
			modifier = Modifier
				.padding(end = 15.dp)
				.fillMaxSize(),
			contentAlignment = Alignment.CenterEnd
		) {
			Card(
				modifier = Modifier.size(35.dp),
				shape = CircleShape,
				elevation = 0.dp,
			) {
				ImagePainter(
					imageUrl = photo,
					isCircle = true,
					errorPhoto = R.drawable.ic_person_fill,
					padding = 8,
					onClick = { onClickUser() }
				)
			}
		}
	}
}