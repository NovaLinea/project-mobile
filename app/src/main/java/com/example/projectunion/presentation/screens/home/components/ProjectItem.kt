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
import com.example.projectunion.domain.model.ProjectTape
import com.example.projectunion.presentation.components.image_painter.ImagePainter
import com.example.projectunion.presentation.components.project_item_information.BodyProject
import com.example.projectunion.presentation.components.project_item_information.HeaderProject

@Composable
fun ProjectItem(
	project: ProjectTape,
    openProfile: () -> Unit,
    openProject: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                HeaderProject(
                    creatorName = project.creatorName,
                    creatorPhoto = project.creatorPhoto,
                    onClickCreator = { openProfile() },
                    time = project.createdAt
                )

                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier.clickable { openProject() },
                ) {
                    BodyProject(
                        title = project.title,
                        description = project.description,
                        maxLines = 5
                    )
                }
            }

            project.images?.let { images ->
                Spacer(modifier = Modifier.height(15.dp))
                Box(
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    ImagePainter(
                        imageUrl = images[0],
                        onClick = { openProject() }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 15.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "${project.price}₽",
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}