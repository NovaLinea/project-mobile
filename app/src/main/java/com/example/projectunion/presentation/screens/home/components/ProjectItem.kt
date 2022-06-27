package com.example.projectunion.presentation.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
    openProfile: (String) -> Unit,
    openProject: () -> Unit
) {
    /*val calendar = Calendar.getInstance()
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val start = "2019-05-24 14:16:00"
    val end = "2019-05-27 16:19:45"
    val mStart = LocalDateTime.parse(TIME_FORMAT.format(Date()))
    val countTime = ChronoUnit.HOURS.between()
    Log.d(TAG, calendar.get(Calendar.YEAR).toString())*/

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        backgroundColor = Color.White,
        elevation = 1.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                HeaderProject(
                    creatorName = project.creatorName,
                    creatorPhoto = project.creatorPhoto,
                    onClickCreator = {
                        project.creatorID?.let { creatorID ->
                            openProfile(creatorID)
                        }
                    },
                    time = project.createdAt
                )

                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier
                        .clickable {
                            openProject()
                        },
                ) {
                    BodyProject(
                        title = project.title,
                        description = project.description,
                        maxLines = 10
                    )

                    Box(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Text(
                            text = "${project.price}₽",
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }

            project.images?.let { images ->
                Box(
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    ImagePainter(
                        imageUrl = images[0],
                        onClick = {
                            openProject()
                        }
                    )
                }
            }
        }
    }
}