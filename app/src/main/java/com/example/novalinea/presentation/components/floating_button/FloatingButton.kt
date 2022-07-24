package com.example.novalinea.presentation.components.floating_button

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import com.example.novalinea.R

@Composable
fun FloatingButton(
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() },
        backgroundColor = colorResource(id = R.color.app_blue),
        contentColor = Color.White
    ){
        Icon(imageVector = imageVector, contentDescription = "Icon floating button")
    }
}