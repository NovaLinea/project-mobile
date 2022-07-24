package com.example.novalinea.presentation.screens.profile.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.Response
import com.example.novalinea.presentation.components.image_painter.ImagePainter
import com.example.novalinea.presentation.components.loader.Loader
import com.example.novalinea.R

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
				errorPhoto = R.drawable.ic_person_fill,
				padding = 7,
				onClick = {
					if (USER.id == id)
						launcher.launch("image/*")
				}
			)
		}
	}
}