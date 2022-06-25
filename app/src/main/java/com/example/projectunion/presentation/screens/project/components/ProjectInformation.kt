package com.example.projectunion.presentation.screens.project.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.domain.model.ProjectOpen
import com.example.projectunion.presentation.components.icon_button.IconButtonAction
import com.example.projectunion.presentation.components.project_item_information.BodyProject
import com.example.projectunion.presentation.components.project_item_information.HeaderProject
import com.example.projectunion.presentation.screens.home.components.ImagePainter
import com.example.projectunion.presentation.screens.home.components.openProject

@Composable
fun ProjectInformation(
	project: ProjectOpen,
	onClickCreator: () -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
	) {
		Column(
			modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
		) {
			HeaderProject(
				creatorName = project.creatorName,
				creatorPhoto = project.creatorPhoto,
				onClickCreator = { onClickCreator() },
				time = project.createdAt
			)

			Spacer(modifier = Modifier.height(10.dp))

			BodyProject(
				title = project.title,
				description = project.description
			)
		}
		Spacer(modifier = Modifier.height(10.dp))

		project.images?.let { images ->
			SliderImagesProject(images = images)
		}

		FooterProject(
			likes = project.likes,
			views = project.views
		)
	}
}

@Composable
fun SliderImagesProject(
	images: List<String>
) {
	if (images.isNotEmpty()) {
		Box(
			modifier = Modifier
				.height(250.dp)
				.fillMaxWidth(),
			contentAlignment = Alignment.Center,
		) {
			ImagePainter(
				imageUrl = images[0],
				onClick = {}
			)
		}
	}
}

@Composable
fun FooterProject(
	likes: Int?,
	views: Int?
) {
	Row(
		modifier = Modifier
			.padding(start = 5.dp, top = 10.dp, end = 15.dp, bottom = 10.dp)
			.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
		) {
			IconButtonAction(
				icon = Icons.Default.FavoriteBorder,
				onClick = {}
			)
			Text(
				text = "$likes",
				style = MaterialTheme.typography.body2
			)

			Box(
				modifier = Modifier.padding(start = 5.dp)
			) {
				IconButtonAction(
					icon = Icons.Default.BookmarkBorder,
					onClick = {}
				)
			}
		}

		Text(
			text = "$views просмотров",
			style = MaterialTheme.typography.body2
		)
	}
}
