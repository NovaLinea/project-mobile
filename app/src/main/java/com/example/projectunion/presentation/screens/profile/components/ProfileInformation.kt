package com.example.projectunion.presentation.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projectunion.R
import com.example.projectunion.domain.model.UserProfile

@Composable
fun ProfileInformation(
	user: UserProfile,
	navController: NavController
) {
	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		AvatarUser(photo = user.photo)
		Spacer(modifier = Modifier.height(5.dp))
		MainDataUser(
			name = user.name,
			description = user.description
		)

		Spacer(modifier = Modifier.height(15.dp))
		ParamsUser(0, 0, 5)

		Spacer(modifier = Modifier.height(15.dp))
		ActionsUser(
			navController = navController
		)

		LazyColumn(
			modifier = Modifier
				.background(colorResource(id = R.color.app_background))
				.fillMaxHeight(),

		) {

		}
	}
}