package com.example.novalinea.presentation.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.novalinea.common.Constants.ARGUMENT_CHAT_DATA
import com.example.novalinea.common.Constants.ARGUMENT_USER_DESCRIPTION_KEY
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.ARGUMENT_USER_NAME_KEY
import com.example.novalinea.common.Constants.AUTHENTICATION_ROUTE
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.ChatOpen
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.UserProfile
import com.example.novalinea.presentation.navigation.MessagesNavRoute
import com.example.novalinea.presentation.navigation.ProfileNavRoute
import com.example.novalinea.presentation.navigation.navigate

@Composable
fun ProfileInformation(
	user: UserProfile,
	photoProfile: String?,
	statePhoto: Response<String?>,
	countProjects: Int,
	navController: NavController,
	onViewingPhoto: () -> Unit
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
			onViewingPhoto = onViewingPhoto
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
		user.id?.let { id ->
			ActionsUser(
				id = id,
				onClick = {
					when(USER.id) {
						id -> {
							navController.popBackStack()
							navController.navigate(
								ProfileNavRoute.EditProfile.route
										+ "?${ARGUMENT_USER_ID_KEY}=${id}"
										+ "&${ARGUMENT_USER_NAME_KEY}=${user.name}"
										+ "&${ARGUMENT_USER_DESCRIPTION_KEY}=${user.description}"
							)
						}
						null -> navController.navigate(AUTHENTICATION_ROUTE)
						else -> {
							navController.navigate(
								MessagesNavRoute.Chat.route
										+ "?${ARGUMENT_USER_ID_KEY}=${id}",
								Pair(
									ARGUMENT_CHAT_DATA,
									ChatOpen(
										userId = user.id,
										userName = user.name,
										userPhoto = user.photo
									)
								)
							)
						}
					}
				}
			)
		}

		Spacer(modifier = Modifier.height(15.dp))
	}
}