package com.example.projectunion.presentation.screens.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projectunion.domain.model.UserProfile

@Composable
fun ProfileInformation(
	user: UserProfile,
	countProjects: Int,
	navController: NavController
) {
	Column(
		modifier = Modifier.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		AvatarUser(photo = user.photo)
		MainDataUser(
			name = user.name,
			description = user.description
		)

		Spacer(modifier = Modifier.height(15.dp))
		ParamsUser(
			countProjects = countProjects,
			follows = user.follows.size,
			followings = user.followings.size)

		Spacer(modifier = Modifier.height(15.dp))
		ActionsUser(
			navController = navController
		)
	}
}