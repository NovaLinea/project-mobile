package com.example.novalinea.presentation.components.type_project

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.R
import com.example.novalinea.common.Constants
import com.example.novalinea.domain.model.TypesProject
import com.example.novalinea.presentation.ui.theme.OpenSans

@Composable
fun TypeProject(
	type: TypesProject
) {
	var typeText: String? = null
	var typeColor = colorResource(id = R.color.app_light_blue)

	when(type) {
		TypesProject.SALE -> {
			typeText = Constants.TYPE_PROJECT_SALE_TEXT
			typeColor = colorResource(id = R.color.app_light_green)
		}
		TypesProject.TEAM -> {
			typeText = Constants.TYPE_PROJECT_TEAM_TEXT
			typeColor = colorResource(id = R.color.app_light_yellow)
		}
		TypesProject.DONATES -> {
			typeText = Constants.TYPE_PROJECT_DONATE_TEXT
			typeColor = colorResource(id = R.color.app_light_blue)
		}
	}

	Card(
		backgroundColor = typeColor,
		elevation = 0.dp,
		shape = RoundedCornerShape(5.dp)
	) {
		typeText?.let { type ->
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
}