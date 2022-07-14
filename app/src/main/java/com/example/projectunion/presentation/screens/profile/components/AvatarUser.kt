package com.example.projectunion.presentation.screens.profile.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectunion.R
import com.example.projectunion.common.Constants.USER
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.image_painter.ImagePainter
import com.example.projectunion.presentation.components.loader.Loader

@Composable
fun AvatarUser(
	id: String,
	photo: String?,
	statePhoto: Response<String?>,
	onChangePhoto: (Uri) -> Unit
) {
	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.GetContent()
	) { uri: Uri? ->
		uri?.let { onChangePhoto(it) }
	}

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
				errorPhoto = R.drawable.ic_person,
				padding = 7,
				onClick = {
					if (USER.id == id)
						launcher.launch("image/*")
				}
			)
		}
	}
}