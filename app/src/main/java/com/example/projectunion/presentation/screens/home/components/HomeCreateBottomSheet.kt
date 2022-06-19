package com.example.projectunion.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.common.Constants
import com.example.projectunion.presentation.navigation.Router
import com.example.projectunion.presentation.screens.home.HomeScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeCreateBottomSheet(
    externalRouter: Router,
) {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetContent = {
            Column(
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 10.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = Constants.TITLE_TYPE_PROJECT,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TypeProjectItem(
                        Constants.TYPE_PROJECT_SALE_TEXT,
                        Icons.Default.AttachMoney,
                        onClick = { externalRouter.navigateTo("create_screen/${Constants.TYPE_PROJECT_SALE}") }
                    )
                    TypeProjectItem(
                        Constants.TYPE_PROJECT_DONATE_TEXT,
                        Icons.Default.CreditCard,
                        onClick = {
                            //externalRouter.navigateTo("create_screen/${Constants.TYPE_PROJECT_DONATE}")
                        }
                    )
                    TypeProjectItem(
                        Constants.TYPE_PROJECT_TEAM_TEXT,
                        Icons.Default.People,
                        onClick = {
                            //externalRouter.navigateTo("create_screen/${Constants.TYPE_PROJECT_TEAM}")
                        }
                    )
                }
            }
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.White,
        sheetElevation = 10.dp,
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
    ) {
        HomeScreen(
            externalRouter = externalRouter,
            onClickCreate = {
                coroutineScope.launch {
                    sheetState.show()
                }
            }
        )
    }
}