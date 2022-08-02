package com.example.novalinea.presentation.components.bottom_sheets

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.novalinea.presentation.navigation.Router
import com.example.novalinea.presentation.screens.profile.components.bottom_sheets.PhotoActions
import com.example.novalinea.presentation.screens.profile.components.bottom_sheets.ProfileActions
import com.example.novalinea.presentation.screens.project.components.bottom_sheets.BuyProject
import com.example.novalinea.presentation.screens.project.components.bottom_sheets.JoinTeamProject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    externalRouter: Router? = null,
    content: @Composable (Router?, (BottomSheetScreen) -> Unit) -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            it != ModalBottomSheetValue.HalfExpanded
        }
    )

    var currentBottomSheet: BottomSheetScreen? by remember {
        mutableStateOf(null)
    }

    val showSheet: (BottomSheetScreen) -> Unit = {
        currentBottomSheet = it
        scope.launch { sheetState.forceExpand() }
    }

    val hideSheet: () -> Unit = {
        scope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            when(currentBottomSheet) {
                is BottomSheetScreen.ProfileActions -> {
                    ProfileActions(
                        userID = (currentBottomSheet as BottomSheetScreen.ProfileActions).userID,
                        navController = (currentBottomSheet as BottomSheetScreen.ProfileActions).navController,
                        onLogout = (currentBottomSheet as BottomSheetScreen.ProfileActions).onLogout,
                        hideBottomSheet = hideSheet,
                    )
                }

                is BottomSheetScreen.PhotoProfileActions -> {
                    PhotoActions(
                        photoIsEmpty = (currentBottomSheet as BottomSheetScreen.PhotoProfileActions).photoIsEmpty,
                        onOpenPhoto = (currentBottomSheet as BottomSheetScreen.PhotoProfileActions).onOpenPhoto,
                        onChangePhoto = { uri ->
                            (currentBottomSheet as BottomSheetScreen.PhotoProfileActions).onChangePhoto(uri)
                        },
                        onDeletePhoto = (currentBottomSheet as BottomSheetScreen.PhotoProfileActions).onDeletePhoto,
                        hideBottomSheet = hideSheet
                    )
                }

                is BottomSheetScreen.BuyProject -> {
                    BuyProject(
                        onSendApplication = (currentBottomSheet as BottomSheetScreen.BuyProject).onSendApplication,
                        hideBottomSheet = hideSheet
                    )
                }

                is BottomSheetScreen.JoinTheTeam -> {
                    JoinTeamProject(
                        staff = (currentBottomSheet as BottomSheetScreen.JoinTheTeam).staff,
                        onSendApplication = (currentBottomSheet as BottomSheetScreen.JoinTheTeam).onSendApplication,
                        hideBottomSheet = hideSheet
                    )
                }

                else -> Text("null")
            }
        },
        sheetBackgroundColor = Color.White,
        sheetElevation = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        content(externalRouter, showSheet)
    }
}

@ExperimentalMaterialApi
suspend fun ModalBottomSheetState.forceExpand() {
    try {
        animateTo(ModalBottomSheetValue.Expanded)
    } catch (e: CancellationException) {
        currentCoroutineContext().ensureActive()
        forceExpand()
    }
}