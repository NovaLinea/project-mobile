package com.example.projectunion.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
	body1 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 17.sp
	),
	body2 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 18.sp,
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
	)
	/*
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)