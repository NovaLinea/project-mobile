package com.example.projectunion.presentation.screens.home.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.domain.model.ProjectTape
import com.example.projectunion.presentation.navigation.Router
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun ProjectItem(
	project: ProjectTape,
	externalRouter: Router
) {
    val today = Date()
    //val countTime = Period.between(today)
    Log.d(TAG, today.toString())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
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
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    project.title?.let {
                        Text(
                            text = it,
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp
                            )
                        )
                    }
                    Text(
                        text = "${project.price}₽",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp
                        )
                    )
                }
                project.description?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(top = 5.dp),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                    )
                }
            }
        }
    }
}