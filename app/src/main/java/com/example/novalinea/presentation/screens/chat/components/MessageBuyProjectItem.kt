package com.example.novalinea.presentation.screens.chat.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.R
import com.example.novalinea.common.Constants.TEXT_APPLICATION_BUY_PROJECT
import com.example.novalinea.common.asPrice
import com.example.novalinea.common.asTime
import com.example.novalinea.presentation.ui.theme.OpenSans

@Composable
fun MessageBuyProjectItem(
	title: String,
	price: Int?,
	time: Any,
	viewed: Boolean,
	locationArrangement: Arrangement.Horizontal
) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = locationArrangement
	) {
		Card(
			modifier = Modifier.fillMaxWidth(0.8f),
			shape = RoundedCornerShape(10.dp),
			elevation = 0.dp,
			backgroundColor = Color.White
		) {
			Column(
				horizontalAlignment = Alignment.End
			) {
				Column(
					modifier = Modifier
						.padding(start = 10.dp, end = 10.dp, top = 6.dp),
					horizontalAlignment = Alignment.Start
				) {
					Text(
						text = TEXT_APPLICATION_BUY_PROJECT,
						style = TextStyle(
							color = Color.Black,
							fontWeight = FontWeight.W600,
							fontSize = 16.sp,
							fontFamily = OpenSans
						)
					)
					Spacer(modifier = Modifier.height(5.dp))
					Text(
						text = title,
						maxLines = 2,
						overflow = TextOverflow.Ellipsis,
						style = TextStyle(
							color = Color.Black,
							fontWeight = FontWeight.W500,
							fontSize = 15.sp,
							fontFamily = OpenSans
						)
					)
					Spacer(modifier = Modifier.height(5.dp))
					price?.let {
						Text(
							text = "${it.asPrice()} ₽",
							style = TextStyle(
								color = Color.Black,
								fontWeight = FontWeight.W500,
								fontSize = 16.sp,
								fontFamily = OpenSans
							)
						)
					}
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