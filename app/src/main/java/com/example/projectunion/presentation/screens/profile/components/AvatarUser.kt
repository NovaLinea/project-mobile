package com.example.projectunion.presentation.screens.profile.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.image_painter.ImagePainter
import com.example.projectunion.presentation.components.loader.Loader

@Composable
fun AvatarUser(
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
		modifier = Modifier
			.width(70.dp)
			.height(70.dp),
		shape = CircleShape,
		elevation = 0.dp,
	) {
		if (statePhoto is Response.Loading)
			Loader()
		else if (photo != null) {
			ImagePainter(
				imageUrl = photo,
				isCircle = true,
				onClick = {
					launcher.launch("image/*")
				}
			)
		}
		else {
			Box(
				modifier = Modifier
					.fillMaxSize()
					.background(Color.LightGray)
					.clickable {
						launcher.launch("image/*")
					},
				contentAlignment = Alignment.Center
			) {
				Icon(
					modifier = Modifier.padding(15.dp),
					imageVector = Icons.Default.Person,
					contentDescription = null
				)
			}
		}
	}
}