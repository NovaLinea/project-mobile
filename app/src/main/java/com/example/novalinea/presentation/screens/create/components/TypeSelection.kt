package com.example.novalinea.presentation.screens.create.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.People
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.common.Constants.TEXT_CHOICE_PROJECT_DONATES
import com.example.novalinea.common.Constants.TITLE_SOON
import com.example.novalinea.common.Constants.TITLE_TYPE_PROJECT
import com.example.novalinea.common.Constants.TYPE_PROJECT_DONATE_TEXT
import com.example.novalinea.common.Constants.TYPE_PROJECT_SALE_TEXT
import com.example.novalinea.common.Constants.TYPE_PROJECT_TEAM_TEXT
import com.example.novalinea.domain.model.TypesProject
import com.example.novalinea.presentation.ui.theme.OpenSans
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TypeSelection(
	nextStep: (TypesProject) -> Unit
) {
	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope()

	Scaffold(
		scaffoldState = scaffoldState,
		snackbarHost = {
			SnackbarHost(it) { data ->
				Snackbar(
					backgroundColor = Color.White,
					contentColor = Color.Black,
					snackbarData = data
				)
			}
		}
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.background(Color.White)
		) {
			Box(
				modifier = Modifier.padding(start = 20.dp, bottom = 7.dp),
				contentAlignment = Alignment.CenterStart
			) {
				Text(
					text = TITLE_TYPE_PROJECT,
					style = MaterialTheme.typography.h6
				)
			}

			Column() {
				TypeItem(
					title = TYPE_PROJECT_TEAM_TEXT,
					icon = Icons.Default.People,
					onClick = { nextStep(TypesProject.TEAM) }
				)

				TypeItem(
					title = TYPE_PROJECT_SALE_TEXT,
					icon = Icons.Default.AttachMoney,
					onClick = { nextStep(TypesProject.SALE) }
				)

				TypeItem(
					title = TYPE_PROJECT_DONATE_TEXT,
					isSoon = true,
					icon = Icons.Default.CreditCard,
					onClick = {
						//nextStep(TypesProject.DONATES)
						scope.launch {
							scaffoldState.snackbarHostState.showSnackbar(TEXT_CHOICE_PROJECT_DONATES)
						}
					}
				)
			}
		}
	}
}

@Composable
fun TypeItem(
	title: String,
	isSoon: Boolean = false,
	icon: ImageVector,
	onClick: () -> Unit
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(50.dp)
			.background(Color.White)
			.clickable { onClick() },
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Start
	) {
		Box(
			modifier = Modifier.padding(start = 20.dp)
		) {
			Icon(imageVector = icon, contentDescription = null)
		}

		Row(
			modifier = Modifier.padding(start = 20.dp)
		) {
			Text(
				text = title,
				style = TextStyle(
					fontFamily = OpenSans,
					fontSize = 17.sp,
					fontWeight = FontWeight.W500,
					color = Color.Black,
				),
			)

			if (isSoon) {
				Box(
					modifier = Modifier
						.padding(start = 5.dp),
					contentAlignment = Alignment.TopStart
				) {
					Text(
						text = TITLE_SOON,
						style = TextStyle(
							fontFamily = OpenSans,
							fontWeight = FontWeight.W400,
							fontSize = 12.sp,
							color = Color.DarkGray
						),
					)
				}
			}
		}
	}
}