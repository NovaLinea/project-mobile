package com.example.projectunion.presentation.screens.project.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projectunion.presentation.components.icon_button.IconButtonAction

@Composable
fun ProjectTopBar(
	navController: NavController
) {
	TopAppBar(
		title = {},
		navigationIcon = {
			IconButtonAction(Icons.Default.ArrowBack) {
				navController.popBackStack()
			}
		},
		/*actions = {
			IconButtonAction(Icons.Default.BookmarkBorder) {

			}
			IconButtonAction(Icons.Default.MoreVert) {

			}
		},*/
		backgroundColor = Color.White,
		elevation = 0.dp
	)
}