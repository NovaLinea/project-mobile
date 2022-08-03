package com.example.novalinea.presentation.screens.project.components.bottom_sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.novalinea.R
import com.example.novalinea.common.Constants.BUTTON_SEND_APPLICATION
import com.example.novalinea.common.Constants.TEXT_JOIN_TEAM_PROJECT
import com.example.novalinea.common.Constants.TITLE_JOIN_TEAM_PROJECT
import com.example.novalinea.presentation.components.button_action.ButtonActionText

@Composable
fun JoinTeamProject(
	staff: List<String>?,
	onSendApplication: (String) -> Unit,
	hideBottomSheet: () -> Unit
) {
	val selectedStaff: MutableState<String?> = remember {
		mutableStateOf(null)
	}
	val listState = rememberLazyListState()

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.White)
	) {
		Row(
			modifier = Modifier
				.padding(top = 10.dp)
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.Center
		) {
			Card(
				modifier = Modifier
					.height(5.dp)
					.width(50.dp),
				backgroundColor = Color.LightGray,
				elevation = 0.dp,
				shape = RoundedCornerShape(10.dp)
			) {}
		}
		Spacer(modifier = Modifier.height(15.dp))

		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 20.dp)
		) {
			Text(
				text = TITLE_JOIN_TEAM_PROJECT,
				style = MaterialTheme.typography.h6
			)

			Spacer(modifier = Modifier.height(10.dp))
			Text(
				text = TEXT_JOIN_TEAM_PROJECT,
				style = MaterialTheme.typography.body2
			)

			Spacer(modifier = Modifier.height(5.dp))
			staff?.let { listStaff ->
				LazyColumn(
					state = listState,
					modifier = Modifier
						.fillMaxWidth()
						.heightIn(max = 300.dp)
				) {
					items(
						items = listStaff,
						key = { staff ->
							staff.hashCode()
						}
					) { item ->
						Row(
							verticalAlignment = Alignment.CenterVertically,
							modifier = Modifier
								.selectable(
									selected = (selectedStaff.value == item),
									onClick = { selectedStaff.value = item },
									role = Role.RadioButton
								)
								.padding(6.dp)
						) {
							IconToggleButton(
								checked = selectedStaff.value == item,
								onCheckedChange = { selectedStaff.value = item },
								modifier = Modifier
									.size(22.dp)
									.padding(top = 2.dp)
							) {
								Icon(
									painter = painterResource(
										if (selectedStaff.value == item)
											R.drawable.ic_circle_checked
										else
											R.drawable.ic_circle_outline
									),
									contentDescription = null,
									tint = colorResource(id = R.color.app_blue)
								)
							}

							Text(
								modifier = Modifier.padding(start = 8.dp),
								text = item,
								style = MaterialTheme.typography.body2
							)
						}
					}
				}
			}

			Spacer(modifier = Modifier.height(15.dp))
			ButtonActionText(
				title = BUTTON_SEND_APPLICATION,
				enabled = selectedStaff.value != null,
				isMaxWidth = true
			) {
				selectedStaff.value?.let { staff ->
					onSendApplication(staff)
					hideBottomSheet()
				}
			}
		}

		Spacer(modifier = Modifier.height(15.dp))
	}
}