package com.example.projectunion.presentation.components.top_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    title: String,
    elevation: Int = 0
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                Alignment.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6
                )
            }
        },
        backgroundColor = Color.White,
        elevation = elevation.dp
    )
}