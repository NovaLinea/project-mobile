package com.example.novalinea.presentation.screens.profile.components.bottom_sheets

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.novalinea.common.Constants.TITLE_CHANGE_PHOTO
import com.example.novalinea.common.Constants.TITLE_DELETE_PHOTO
import com.example.novalinea.common.Constants.TITLE_OPEN_PHOTO
import com.example.novalinea.presentation.components.bottom_sheets.BottomSheetItem

@Composable
fun PhotoActions(
	photoIsEmpty: Boolean,
	onOpenPhoto: () -> Unit,
	onChangePhoto: (Uri) -> Unit,
	onDeletePhoto: () -> Unit,
	hideBottomSheet: () -> Unit
) {
	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.GetContent()
	) { uri: Uri? ->
		uri?.let { onChangePhoto(it) }
	}

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
		Spacer(modifier = Modifier.height(10.dp))

		if (!photoIsEmpty) {
			BottomSheetItem(
				title = TITLE_OPEN_PHOTO,
				icon = Icons.Default.OpenInFull,
				onClick = {
					hideBottomSheet()
					onOpenPhoto()
				}
			)
		}

		BottomSheetItem(
			title = TITLE_CHANGE_PHOTO,
			icon = Icons.Default.Upload,
			onClick = {
				hideBottomSheet()
				launcher.launch("image/*")
			}
		)

		if (!photoIsEmpty) {
			BottomSheetItem(
				title = TITLE_DELETE_PHOTO,
				icon = Icons.Default.Delete,
				onClick = {
					hideBottomSheet()
					onDeletePhoto()
				}
			)
		}

		Spacer(modifier = Modifier.height(15.dp))
	}
}