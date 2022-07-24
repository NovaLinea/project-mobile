package com.example.novalinea.presentation.screens.project.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.presentation.ui.theme.OpenSans

@Composable
fun FeedbackProject(
	views: Int?
) {
	Row(
		modifier = Modifier
			.padding(end = 15.dp, bottom = 15.dp)
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
				contentDescription = null,
				tint = Color.DarkGray
			)
			Text(
				modifier = Modifier.padding(start = 5.dp),
				text = "$views",
				style = TextStyle(
					fontFamily = OpenSans,
					fontWeight = FontWeight.W400,
					fontSize = 16.sp
				),
				color = Color.DarkGray
			)
		}
	}
}