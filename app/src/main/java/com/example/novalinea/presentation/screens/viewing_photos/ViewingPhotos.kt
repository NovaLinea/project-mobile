package com.example.novalinea.presentation.screens.viewing_photos

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import com.example.novalinea.R
import com.example.novalinea.domain.model.Photos
import com.example.novalinea.presentation.components.image_painter.ImagePainter
import com.example.novalinea.presentation.screens.viewing_photos.components.ViewingTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ViewingPhotos(
	photos: Photos?,
	navController: NavController
) {
	Scaffold(
		topBar = {
			ViewingTopBar {
				navController.popBackStack()
			}
		}
	) {
		photos?.photos?.let { photos ->
			Box(
				modifier = Modifier
					.fillMaxSize()
					.background(Color.Black)
			) {
				ImagePainter(
					imageUrl = photos[0],
					onClick = {},
					padding = 0,
					errorPhoto = R.drawable.ic_person_fill,
					contentScale = ContentScale.FillWidth,
					loader = true
				)
			}
		}
	}
}