package com.example.projectunion.presentation.screens.profile.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.lifecycle.MutableLiveData
import com.example.projectunion.presentation.screens.home.components.ImagePainter

@Composable
fun AvatarUser(photo: String?) {
	/*val photoUri = MutableLiveData<Uri?>(null)
	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.GetContent()
	) { uri: Uri? ->
		uri?.let { photoUri.value = it }
	}*/

	Box(
		modifier = Modifier
			.width(70.dp)
			.height(70.dp),
		contentAlignment = Alignment.Center
	) {
//		if (photoUri.value != null) {
//			ImagePainter(
//				imageUrl = photoUri.value!!,
//				isCircle = true,
//				onClick = {
//					//launcher.launch("image/*")
//				}
//			)
//		}
		if (photo != null) {
			ImagePainter(
				imageUrl = photo,
				isCircle = true,
				onClick = {
					//launcher.launch("image/*")
				}
			)
		}
		else {
			Card(
				modifier = Modifier
					.fillMaxSize()
					.clickable {
						//launcher.launch("image/*")
					},
				shape = CircleShape,
				elevation = 0.dp,
				backgroundColor = Color.LightGray
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