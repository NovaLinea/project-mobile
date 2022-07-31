package com.example.novalinea.presentation.screens.create.components.steps

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.novalinea.R
import com.example.novalinea.common.Constants.TITLE_PRICE_PROJECT
import com.example.novalinea.common.Constants.MAX_COUNT_STAFF
import com.example.novalinea.common.Constants.TEXT_ADDED_MAX_COUNT_STAFF
import com.example.novalinea.common.Constants.TITLE_STAFF_PROJECT
import com.example.novalinea.common.asPrice
import com.example.novalinea.domain.model.TypesProject
import com.example.novalinea.presentation.components.close_button.CloseButton
import com.example.novalinea.presentation.screens.create.components.ButtonNextStep
import com.example.novalinea.presentation.screens.create.components.create_text_field.CreatePriceField
import com.example.novalinea.presentation.screens.create.components.create_text_field.CreateStaffField
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdditionallyInformationProject(
	type: TypesProject,
	price: String,
	onPriceChange: (String) -> Unit,
	listStaff: SnapshotStateList<String>,
	enabled: Boolean,
	nextStep: () -> Unit
) {
	val focusManager = LocalFocusManager.current
	val listState = rememberLazyListState()

	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope()

	Scaffold(
		bottomBar = {
			ButtonNextStep(
				enabled = enabled,
				nextStep = nextStep
			)
		},
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
		LazyColumn(
			state = listState,
			modifier = Modifier
				.padding(top = 5.dp)
				.fillMaxSize()
		) {
			item {
				var title = ""
				if (type == TypesProject.SALE)
					title = TITLE_PRICE_PROJECT
				else if (type == TypesProject.TEAM)
					title = TITLE_STAFF_PROJECT

				Box(
					modifier = Modifier.padding(start = 15.dp, bottom = 15.dp),
					contentAlignment = Alignment.CenterStart
				) {
					Text(
						text = title,
						style = MaterialTheme.typography.h6
					)
				}
			}

			if (type == TypesProject.SALE) {
				item {
					var formatPrice = ""
					if (price != "")
						formatPrice =  price.replace(" ", "").toInt().asPrice()

					Box(
						modifier = Modifier.padding(start = 15.dp, end = 15.dp)
					) {
						CreatePriceField(
							value = formatPrice,
							onValueChange = onPriceChange,
							focusManager = focusManager
						)
					}
				}
			}

			else if (type == TypesProject.TEAM) {
				item {
					Box(
						modifier = Modifier.padding(start = 15.dp, end = 15.dp)
					) {
						CreateStaffField(
							addStaff = { staff ->
								if (listStaff.size < MAX_COUNT_STAFF)
									listStaff.add(staff)
								else {
									scope.launch {
										scaffoldState.snackbarHostState.showSnackbar(TEXT_ADDED_MAX_COUNT_STAFF)
									}
								}
							}
						)
					}
					Spacer(modifier = Modifier.height(10.dp))
				}

				itemsIndexed(
					items = listStaff,
					key = { index, _ ->
						index.hashCode()
					}
				) { index, staff ->
					Row(
						modifier = Modifier
							.height(35.dp)
							.padding(vertical = 5.dp, horizontal = 15.dp)
							.fillMaxWidth(),
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceBetween
					) {
						Row(
							modifier = Modifier
								.padding(start = 5.dp)
								.fillMaxHeight(),
							verticalAlignment = Alignment.CenterVertically
						) {
							Icon(
								modifier = Modifier.size(7.dp),
								painter = painterResource(id = R.drawable.ic_circle),
								contentDescription = null
							)

							Text(
								modifier = Modifier.padding(start = 10.dp),
								text = staff,
								style = MaterialTheme.typography.body1
							)
						}
						Card(
							modifier = Modifier.size(20.dp),
							backgroundColor = colorResource(id = R.color.app_background),
							shape = CircleShape,
							elevation = 0.dp
						) {
							Box(
								modifier = Modifier.padding(3.dp)
							) {
								CloseButton {
									listStaff.removeAt(index)
								}
							}
						}
					}
				}

				item {
					Spacer(modifier = Modifier.height(60.dp))
				}
			}
		}
	}
}