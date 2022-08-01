package com.example.novalinea.presentation.components.staff_project

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.R
import com.example.novalinea.common.Constants.TITLE_REQUIRED
import com.example.novalinea.presentation.ui.theme.OpenSans

@Composable
fun StaffProject(
	staff: List<String>,
	isAllVisible: Boolean = false
) {
	Column(
		modifier = Modifier.fillMaxWidth()
	) {
		if (!isAllVisible) {
			Text(
				text = "${TITLE_REQUIRED}:",
				style = TextStyle(
					fontFamily = OpenSans,
					fontWeight = FontWeight.W500,
					fontSize = 16.sp
				)
			)
			Spacer(modifier = Modifier.height(5.dp))

			staff.forEachIndexed { index, staff ->
				if (index < 3) {
					Row(
						modifier = Modifier.padding(start = 5.dp),
						verticalAlignment = Alignment.CenterVertically
					) {
						Icon(
							modifier = Modifier.size(7.dp),
							painter = painterResource(id = R.drawable.ic_circle),
							contentDescription = null
						)

						Text(
							modifier = Modifier.padding(start = 10.dp),
							text = staff,
							style = TextStyle(
								fontFamily = OpenSans,
								fontWeight = FontWeight.W400,
								fontSize = 15.sp
							)
						)
					}

					if (index != 2)
						Spacer(modifier = Modifier.height(5.dp))
				}
				else if (index == 3 && index != staff.lastIndex) {
					Text(
						modifier = Modifier.padding(start = 5.dp),
						text = "...",
						style = TextStyle(
							fontFamily = OpenSans,
							fontWeight = FontWeight.W500,
							fontSize = 18.sp
						)
					)
				}
			}
		}
		else {
			Text(
				text = "${TITLE_REQUIRED}:",
				style = TextStyle(
					fontFamily = OpenSans,
					fontWeight = FontWeight.W600,
					fontSize = 17.sp
				)
			)
			Spacer(modifier = Modifier.height(5.dp))

			staff.forEach { staff ->
				Row(
					modifier = Modifier.padding(start = 5.dp),
					verticalAlignment = Alignment.CenterVertically
				) {
					Icon(
						modifier = Modifier.size(7.dp),
						painter = painterResource(id = R.drawable.ic_circle),
						contentDescription = null
					)

					Text(
						modifier = Modifier.padding(start = 10.dp),
						text = staff,
						style = TextStyle(
							fontFamily = OpenSans,
							fontWeight = FontWeight.W400,
							fontSize = 16.sp
						)
					)
				}
				Spacer(modifier = Modifier.height(8.dp))
			}
		}
	}
}