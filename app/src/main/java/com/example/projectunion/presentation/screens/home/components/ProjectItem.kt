package com.example.projectunion.presentation.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projectunion.common.asPrice
import com.example.projectunion.domain.model.ProjectTape
import com.example.projectunion.presentation.components.image_painter.ImagePainter
import com.example.projectunion.presentation.components.creator_project.CreatorProject

@Composable
fun ProjectItem(
	project: ProjectTape,
    openProfile: () -> Unit,
    openProject: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 12.dp)
            .clickable { openProject() },
        backgroundColor = Color.White,
        elevation = 3.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CreatorProject(
                        creatorName = project.creatorName,
                        creatorPhoto = project.creatorPhoto,
                        onClickCreator = { openProfile() }
                    )

                    project.price?.let {
                        Text(
                            text = "${it.asPrice()} ₽",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }

                Spacer(modifier = Modifier.height(7.dp))

                Column() {
                    BodyProject(
                        title = project.title,
                        description = project.description
                    )
                }
            }

            project.images?.let { images ->
                Box(
                    modifier = Modifier
                        .height(225.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    ImagePainter(
                        imageUrl = images[0],
                        onClick = { openProject() }
                    )
                }
            }
        }
    }
}