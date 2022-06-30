package com.example.projectunion.presentation.screens.project.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectunion.presentation.components.icon_button.IconButtonAction

@Composable
fun FeedbackProject(
	likes: Int?,
	views: Int?
) {
	Row(
		modifier = Modifier
			.padding(start = 5.dp, top = 10.dp, end = 15.dp, bottom = 10.dp)
			.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.End
	) {
		/*Row(
			verticalAlignment = Alignment.CenterVertically,
		) {
			IconButtonAction(
				icon = Icons.Default.FavoriteBorder,
				onClick = {}
			)
			Text(
				text = "$likes",
				style = MaterialTheme.typography.body1
			)

			Box(
				modifier = Modifier.padding(start = 5.dp)
			) {
				IconButtonAction(
					icon = Icons.Default.BookmarkBorder,
					onClick = {}
				)
			}
		}*/

		Row(
			verticalAlignment = Alignment.CenterVertically,
		) {
			Icon(
				imageVector = Icons.Default.RemoveRedEye,
				contentDescription = null
			)
			Text(
				modifier = Modifier.padding(start = 5.dp),
				text = "$views",
				style = MaterialTheme.typography.body1
			)
		}
	}
}