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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.common.Constants.PROFILE_SCREEN_ROUTE
import com.example.projectunion.common.Constants.PROJECT_SCREEN_ROUTE
import com.example.projectunion.domain.model.ProjectTape
import com.example.projectunion.presentation.navigation.Router

@Composable
fun ProjectItem(
	project: ProjectTape,
	externalRouter: Router
) {
    val maxCharDescription = 350

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
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .clickable {
                                externalRouter.navigateTo("${PROFILE_SCREEN_ROUTE}/${project.creatorID}")
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        project.creatorPhoto?.let { photo ->
                            Box(
                                modifier = Modifier
                                    .height(25.dp)
                                    .width(25.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                ImagePainter(
                                    imageUrl = photo,
                                    shape = 10f
                                    //isCircle = true
                                ) {}
                            }
                        }

                        Text(
                            text = project.creatorName.toString(),
                            modifier = Modifier.padding(start = 10.dp),
                            style = TextStyle(
                                color = Color.DarkGray,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500
                            )
                        )
                    }

                    Text(
                        text = "time",
                        style = TextStyle(
                            color = Color.DarkGray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500
                        )
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .clickable {
                            externalRouter.navigateTo("${PROJECT_SCREEN_ROUTE}/${project.id}")
                        },
                ) {
                    project.title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.h6
                        )
                    }
                    project.description?.let {
                        var description = if (it.length > maxCharDescription)
                            it.substring(0..maxCharDescription) + "..."
                        else
                            it
                        Text(
                            text = description,
                            modifier = Modifier.padding(top = 5.dp),
                            style = MaterialTheme.typography.body1
                        )
                    }

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
                    ImagePainter(imageUrl = images[0]) {
                        externalRouter.navigateTo("${PROJECT_SCREEN_ROUTE}/${project.id}")
                    }
                }
            }
        }
    }
}