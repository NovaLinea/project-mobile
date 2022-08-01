package com.example.novalinea.presentation.screens.home.components

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.common.asPrice
import com.example.novalinea.domain.model.ProjectTape
import com.example.novalinea.domain.model.TypesProject
import com.example.novalinea.presentation.components.image_painter.ImagePainter
import com.example.novalinea.presentation.components.creator_project.CreatorProject
import com.example.novalinea.presentation.components.staff_project.StaffProject
import com.example.novalinea.presentation.components.type_project.TypeProject
import com.example.novalinea.presentation.ui.theme.OpenSans

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
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CreatorProject(
                        creatorName = project.creatorName,
                        creatorPhoto = project.creatorPhoto,
                        onClickCreator = { openProfile() }
                    )

                    if (project.type == TypesProject.SALE) {
                        project.price?.let {
                            Text(
                                text = "${it.asPrice()} ₽",
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                project.type?.let { type ->
                    TypeProject(type = type)
                }
                Spacer(modifier = Modifier.height(4.dp))

                project.title?.let { title ->
                    Text(
                        text = title,
                        style = TextStyle(
                            fontFamily = OpenSans,
                            fontWeight = FontWeight.W600,
                            fontSize = 18.sp
                        ),
                        overflow = TextOverflow.Ellipsis,
                        letterSpacing = 0.2.sp,
                        lineHeight = 25.sp
                    )
                }

                if (project.type == TypesProject.SALE || project.type == TypesProject.DONATES) {
                    if (project.description != null && project.description.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = project.description,
                            maxLines = 5,
                            style = TextStyle(
                                fontFamily = OpenSans,
                                fontWeight = FontWeight.W400,
                                fontSize = 15.sp
                            ),
                            overflow = TextOverflow.Ellipsis,
                            letterSpacing = 0.2.sp,
                            lineHeight = 25.sp
                        )
                    }
                }
                else if (project.type == TypesProject.TEAM) {
                    project.staff?.let { staff ->
                        Spacer(modifier = Modifier.height(7.dp))
                        StaffProject(staff = staff)
                    }
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