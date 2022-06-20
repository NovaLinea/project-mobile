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
import com.example.projectunion.domain.model.ProjectTape
import com.example.projectunion.presentation.navigation.Router

@Composable
fun ProjectItem(
	project: ProjectTape,
	externalRouter: Router
) {
    val maxCharDescription = 350
    //val today = Date()
    //val countTime = Period.between(today)
    //Log.d(TAG, today.toString())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Creator name",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = project.createdAt.toString(),
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                )
            }

            Column(
                modifier = Modifier.clickable {
                    externalRouter.navigateTo("project_screen/${project.id}")
                },
            ) {
                project.title?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(top = 10.dp),
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
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.W500,
                            fontSize = 20.sp
                        )
                    )
                }
            }
        }
    }
}