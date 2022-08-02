package com.example.novalinea.presentation.screens.chat.components.message_item

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.R
import com.example.novalinea.common.asTime
import com.example.novalinea.presentation.ui.theme.OpenSans

@Composable
fun MessageTextItem(
	message: String,
	time: Any,
	viewed: Boolean,
	locationArrangement: Arrangement.Horizontal
) {
	val configuration = LocalConfiguration.current
	val screenWidth = configuration.screenWidthDp.dp

	Row(
		modifier = Modifier.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = locationArrangement
	) {
		Card(
			modifier = Modifier.widthIn(max = screenWidth * 0.8f),
			shape = RoundedCornerShape(10.dp),
			elevation = 0.dp,
			backgroundColor = Color.White
		) {
			Column(
				horizontalAlignment = Alignment.End
			) {
				Box(
					modifier = Modifier
						.padding(start = 10.dp, end = 10.dp, top = 6.dp)
				) {
					Text(
						text = message,
						style = TextStyle(
							color = Color.Black,
							fontWeight = FontWeight.W400,
							fontSize = 15.sp,
							fontFamily = OpenSans
						)
					)
				}

				Box(
					modifier = Modifier
						.padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
				) {
					Row(
						verticalAlignment = Alignment.CenterVertically
					) {
						Text(
							text = time.toString().asTime(),
							style = TextStyle(
								fontFamily = OpenSans,
								fontWeight = FontWeight.W400,
								fontSize = 11.sp,
								color = Color.DarkGray
							)
						)

						Box(
							modifier = Modifier
								.padding(start = 3.dp)
								.size(18.dp)
						) {
							var iconViewed = R.drawable.ic_check
							if (viewed)
								iconViewed = R.drawable.ic_double_check

							Icon(
								painter = painterResource(id = iconViewed),
								contentDescription = null,
								tint = colorResource(id = R.color.app_green)
							)
						}
					}
				}
			}
		}
	}
}