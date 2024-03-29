package com.example.novalinea.presentation.components.bottom_sheets

import android.net.Uri
import androidx.navigation.NavController

sealed class BottomSheetScreen {
	class ProfileActions(
		val userID: String,
		val navController: NavController,
		val onLogout: () -> Unit,
	): BottomSheetScreen()

	class PhotoProfileActions(
		val photoIsEmpty: Boolean,
		val onOpenPhoto: () -> Unit,
		val onChangePhoto: (Uri) -> Unit,
		val onDeletePhoto: () -> Unit
	): BottomSheetScreen()

	class BuyProject(
		val onSendApplication: () -> Unit
	): BottomSheetScreen()

	class JoinTheTeam(
		val staff: List<String>?,
		val onSendApplication: (String) -> Unit
	): BottomSheetScreen()
}