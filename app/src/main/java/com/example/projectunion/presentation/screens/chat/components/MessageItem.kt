package com.example.projectunion.presentation.screens.chat.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.common.asTime
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessageItem(
	message: String,
	time: Any,
	locationArrangement: Arrangement.Horizontal
) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = locationArrangement
	) {
		Card(
			//modifier = Modifier.fillMaxWidth(0.8f),
			shape = RoundedCornerShape(10.dp),
			elevation = 0.dp,
			backgroundColor = Color.White
		) {
			Column(
				modifier = Modifier
					.padding(horizontal = 12.dp, vertical = 8.dp),
				horizontalAlignment = Alignment.End
			) {
				Text(
					text = message,
					style = TextStyle(
						color = Color.Black,
						fontWeight = FontWeight.Normal,
						fontSize = 15.sp
					)
				)

				Text(
					modifier = Modifier.padding(top = 2.dp),
					text = time.toString().asTime(),
					style = TextStyle(
						color = Color.DarkGray,
						fontWeight = FontWeight.Normal,
						fontSize = 13.sp
					)
				)
			}
		}
	}
}