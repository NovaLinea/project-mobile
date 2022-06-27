package com.example.projectunion.presentation.screens.profile.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserProfile

@Composable
fun ProfileInformation(
	user: UserProfile,
	photoProfile: String?,
	statePhoto: Response<String?>,
	countProjects: Int,
	navController: NavController,
	onChangePhoto: (Uri) -> Unit,
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.White),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		AvatarUser(
			photo = photoProfile,
			statePhoto = statePhoto,
			onChangePhoto = onChangePhoto
		)

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

		Spacer(modifier = Modifier.height(15.dp))
	}
}