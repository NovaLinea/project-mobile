package com.example.novalinea.presentation.components.type_project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.R
import com.example.novalinea.common.Constants.TYPE_PROJECT_DONATE_TEXT
import com.example.novalinea.common.Constants.TYPE_PROJECT_SALE_TEXT
import com.example.novalinea.common.Constants.TYPE_PROJECT_TEAM_TEXT
import com.example.novalinea.common.asPrice
import com.example.novalinea.domain.model.TypesProject
import com.example.novalinea.presentation.ui.theme.OpenSans

@Composable
fun TypeProject(
	type: TypesProject,
	price: Int? = null
) {
	var typeText: String? = null
	var typeColor = colorResource(id = R.color.app_light_blue)

	when(type) {
		TypesProject.SALE -> {
			typeText = TYPE_PROJECT_SALE_TEXT
			typeColor = colorResource(id = R.color.app_light_green)
		}
		TypesProject.TEAM -> {
			typeText = TYPE_PROJECT_TEAM_TEXT
			typeColor = colorResource(id = R.color.app_light_yellow)
		}
		TypesProject.DONATES -> {
			typeText = TYPE_PROJECT_DONATE_TEXT
			typeColor = colorResource(id = R.color.app_light_blue)
		}
	}

	Row(
		modifier = Modifier.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically
	) {
		typeText.let { type ->
			Card(
				backgroundColor = typeColor,
				elevation = 0.dp,
				shape = RoundedCornerShape(5.dp)
			) {
				Text(
					modifier = Modifier
						.padding(vertical = 4.dp, horizontal = 7.dp),
					text = type,
					style = TextStyle(
						fontFamily = OpenSans,
						fontWeight = FontWeight.W400,
						fontSize = 14.sp
					),
					color = Color.DarkGray
				)
			}
		}

		price?.let {
			Spacer(modifier = Modifier.width(5.dp))

			Card(
				backgroundColor = colorResource(id = R.color.app_light_yellow),
				elevation = 0.dp,
				shape = RoundedCornerShape(5.dp)
			) {
				Text(
					modifier = Modifier
						.padding(vertical = 4.dp, horizontal = 7.dp),
					text = "${it.asPrice()} ₽",
					style = TextStyle(
						fontFamily = OpenSans,
						fontWeight = FontWeight.W400,
						fontSize = 14.sp
					),
					color = Color.DarkGray
				)
			}
		}
	}
}