package com.example.novalinea.presentation.screens.create.components

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.novalinea.common.Constants.BUTTON_CREATE_PROJECT
import com.example.novalinea.common.Constants.MAX_IMAGES_PROJECT
import com.example.novalinea.common.Constants.PRICE_PROJECT_PLACEHOLDER
import com.example.novalinea.common.asPrice
import com.example.novalinea.domain.model.TypesProject
import com.example.novalinea.presentation.components.button_action.ButtonActionText
import com.example.novalinea.presentation.components.close_button.CloseButton
import com.example.novalinea.presentation.components.image_painter.ImagePainter
import com.example.novalinea.presentation.screens.create.components.create_text_field.CreatePriceField

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdditionallyInformationProject(
	type: TypesProject,
	price: String,
	onPriceChange: (String) -> Unit,
	images: List<Uri>,
	onAddImage: (Uri) -> Unit,
	onDeleteImage: (Int) -> Unit,
	enabled: Boolean,
	onCreate: () -> Unit
) {
	val focusManager = LocalFocusManager.current
	val listState = rememberLazyListState()

	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.GetContent()
	) { uri: Uri? ->
		uri?.let { onAddImage(it) }
	}

	Scaffold(
		bottomBar = {
			Box(
				modifier = Modifier
					.padding(end = 10.dp, bottom = 10.dp)
					.fillMaxWidth(),
				contentAlignment = Alignment.CenterEnd
			) {
				ButtonActionText(
					title = BUTTON_CREATE_PROJECT,
					enabled = enabled
				) {
					onCreate()
				}
			}
		}
	) {
		Column(
			modifier = Modifier
				.padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 50.dp)
		) {
			if (type == TypesProject.SALE) {
				var formatPrice = ""

				if (price != "")
					formatPrice =  price.replace(" ", "").toInt().asPrice()

				CreatePriceField(
					value = formatPrice,
					placeholder = PRICE_PROJECT_PLACEHOLDER,
					onValueChange = onPriceChange,
					textStyle = MaterialTheme.typography.body1,
					focusManager = focusManager
				)
			}

			Spacer(modifier = Modifier.height(20.dp))

			LazyRow(
				state = listState,
				modifier = Modifier.fillMaxWidth(),
				contentPadding = PaddingValues(horizontal = 2.dp),
				horizontalArrangement = Arrangement.spacedBy(8.dp)
			) {
				itemsIndexed(
					items = images,
					key = { index, _ ->
						index
					}
				) { index, imageUri ->
					Card(
						modifier = Modifier
							.height(70.dp)
							.width(100.dp),
						backgroundColor = Color.White,
						elevation = 5.dp,
						shape = RoundedCornerShape(10.dp)
					) {
						ImagePainter(
							imageUrl = imageUri,
							shape = 10f,
							onClick = {}
						)
						Box(
							modifier = Modifier.fillMaxWidth(),
							contentAlignment = Alignment.TopEnd
						) {
							Card(
								modifier = Modifier
									.padding(3.dp)
									.height(15.dp)
									.width(15.dp),
								backgroundColor = Color.Red,
								elevation = 0.dp,
								shape = RoundedCornerShape(10.dp)
							) {
								Box(
									modifier = Modifier.padding(2.dp)
								) {
									CloseButton {
										onDeleteImage(index)
									}
								}
							}
						}
					}
				}

				if (images.size < MAX_IMAGES_PROJECT) {
					item {
						Card(
							modifier = Modifier
								.height(70.dp)
								.width(100.dp)
								.clickable {
									launcher.launch("image/*")
								},
							backgroundColor = Color.White,
							elevation = 5.dp,
							shape = RoundedCornerShape(10.dp)
						) {
							Box(
								modifier = Modifier.fillMaxWidth(),
								contentAlignment = Alignment.Center
							) {
								Icon(
									imageVector = Icons.Default.Add,
									contentDescription = null,
									tint = Color.DarkGray
								)
							}
						}
					}
				}
			}
		}
	}
}