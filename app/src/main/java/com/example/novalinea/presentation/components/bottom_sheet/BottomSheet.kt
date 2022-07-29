package com.example.novalinea.presentation.components.bottom_sheet

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.novalinea.presentation.navigation.Router
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    externalRouter: Router? = null,
    sheetContent: @Composable (Router?, () -> Unit) -> Unit,
    content: @Composable (Router?, () -> Unit) -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            it != ModalBottomSheetValue.HalfExpanded
        }
    )

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            sheetContent(externalRouter) {
                scope.launch {
                    if (sheetState.isVisible) {
                        sheetState.hide()
                    } else {
                        sheetState.forceExpand()
                    }
                }
            }
        },
        sheetBackgroundColor = Color.White,
        sheetElevation = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        content(
            externalRouter
        ) {
            scope.launch {
                if (sheetState.isVisible) {
                    sheetState.hide()
                } else {
                    sheetState.forceExpand()
                }
            }
        }
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