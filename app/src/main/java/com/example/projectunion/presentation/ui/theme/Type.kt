package com.example.projectunion.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.projectunion.R

val Mukta = FontFamily(
	Font(R.font.mukta_light, FontWeight.W300),
	Font(R.font.mukta_regular, FontWeight.W400),
	Font(R.font.mukta_medium, FontWeight.W500),
	Font(R.font.mukta_bold, FontWeight.W600)
)

val Quicksand = FontFamily(
	Font(R.font.quicksand_light, FontWeight.W300),
	Font(R.font.quicksand_regular, FontWeight.W400),
	Font(R.font.quicksand_medium, FontWeight.W500),
	Font(R.font.quicksand_bold, FontWeight.W600)
)

val Raleway = FontFamily(
	Font(R.font.raleway_light, FontWeight.W300),
	Font(R.font.raleway_regular, FontWeight.W400),
	Font(R.font.raleway_medium, FontWeight.W500),
	Font(R.font.raleway_bold, FontWeight.W600)
)

val Typography = Typography(
	body1 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.W400,
		fontSize = 17.sp
	),
	body2 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.W400,
		fontSize = 16.sp,
		color = Color.DarkGray
	),
	h5 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.W600,
		fontSize = 22.sp
	),
	h6 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.W600,
		fontSize = 20.sp
	),
	caption = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.W400,
		fontSize = 13.sp,
		color = Color.DarkGray
	),
	subtitle1 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.W600,
		fontSize = 17.sp
	),
	subtitle2 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.W500,
		fontSize = 18.sp
	),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp
    ),
)