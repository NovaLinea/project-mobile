package com.example.novalinea.presentation.screens.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.novalinea.presentation.components.icon_button.IconButtonAction

@Composable
fun ProfileTopBar(
	isBack: Boolean,
	onClickBack: () -> Unit,
	showBottomSheet: () -> Unit
) {
	Box(
		modifier = Modifier
			.height(55.dp)
			.fillMaxWidth()
	) {
		if (isBack) {
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
		}

		Box(
			modifier = Modifier
				.padding(end = 5.dp)
				.fillMaxSize(),
			contentAlignment = Alignment.CenterEnd
		) {
			IconButtonAction(Icons.Default.MoreVert) {
				showBottomSheet()
			}
		}
	}
}