package com.example.projectunion.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.R

@Composable
fun SearchScreen() {
	Scaffold(
		topBar = {
			TopAppBar(
				backgroundColor = Color.White,
				elevation = 0.dp
			) {
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.Center
				) {
					Text(
						text = stringResource(id = R.string.search_screen),
						style = TextStyle(
							color = Color.Black,
							fontWeight = FontWeight.W600,
							fontSize = 18.sp
						)
					)
				}
			}
		},
	) {
		Search()
	}
}