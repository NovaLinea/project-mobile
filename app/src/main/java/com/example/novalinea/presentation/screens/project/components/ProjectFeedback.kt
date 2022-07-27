package com.example.novalinea.presentation.screens.project.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.common.asViews
import com.example.novalinea.presentation.ui.theme.OpenSans

@Composable
fun ProjectFeedback(
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

		Text(
			text = "${views?.asViews()}",
			style = TextStyle(
				fontFamily = OpenSans,
				fontWeight = FontWeight.W400,
				fontSize = 14.sp,
				color = Color.Gray
			)
		)
	}
}