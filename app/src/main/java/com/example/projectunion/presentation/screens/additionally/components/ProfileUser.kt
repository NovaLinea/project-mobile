package com.example.projectunion.presentation.screens.additionally.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projectunion.R
import com.example.projectunion.presentation.components.image_painter.ImagePainter

@Composable
fun ProfileUser(
    name: String?,
    photo: String?,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                elevation = 0.dp,
            ) {
                ImagePainter(
                    imageUrl = photo,
                    isCircle = true,
                    padding = 12,
                    errorPhoto = R.drawable.ic_person_fill,
                    onClick = { onClick() }
                )
            }

            name?.let { name ->
                Text(
                    text = name,
                    modifier = Modifier.padding(start = 20.dp),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(vertical = 15.dp, horizontal = 20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowRight,
                contentDescription = null,
                tint = Color.DarkGray
            )
        }
    }
}