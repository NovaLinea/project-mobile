package com.example.novalinea.presentation.screens.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.novalinea.domain.model.Response
import com.example.novalinea.presentation.components.image_painter.ImagePainter
import com.example.novalinea.presentation.components.loader.Loader
import com.example.novalinea.R

@Composable
fun AvatarUser(
	photo: String?,
	statePhoto: Response<String?>,
	onClickAvatar: () -> Unit
) {
	Card(
		modifier = Modifier.size(70.dp),
		shape = CircleShape,
		elevation = 0.dp,
	) {
		if (statePhoto is Response.Loading)
			Loader()
		else {
			ImagePainter(
				imageUrl = photo,
				isCircle = true,
				errorPhoto = R.drawable.ic_person_fill,
				padding = 7,
				onClick = {
					onClickAvatar()
				}
			)
		}
	}
}