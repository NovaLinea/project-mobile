package com.example.novalinea.presentation.screens.home.components

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
import androidx.compose.ui.unit.dp
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_TYPE_KEY
import com.example.novalinea.common.Constants.TITLE_TYPE_PROJECT
import com.example.novalinea.common.Constants.TYPE_PROJECT_DONATE_TEXT
import com.example.novalinea.common.Constants.TYPE_PROJECT_SALE
import com.example.novalinea.common.Constants.TYPE_PROJECT_SALE_TEXT
import com.example.novalinea.common.Constants.TYPE_PROJECT_TEAM_TEXT
import com.example.novalinea.presentation.navigation.HomeNavRoute
import com.example.novalinea.presentation.navigation.Router
import com.example.novalinea.presentation.screens.home.HomeScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeCreateBottomSheet(
    externalRouter: Router
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
                        .padding(top = 20.dp, bottom = 10.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = TITLE_TYPE_PROJECT,
                        style = MaterialTheme.typography.h6
                    )
                }

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TypeProjectItem(
                        TYPE_PROJECT_SALE_TEXT,
                        Icons.Default.AttachMoney,
                        onClick = {
                            externalRouter.routeTo(
                                HomeNavRoute.Create.route + "?${ARGUMENT_PROJECT_TYPE_KEY}=${TYPE_PROJECT_SALE}"
                            )
                        }
                    )
                    TypeProjectItem(
                        TYPE_PROJECT_DONATE_TEXT,
                        Icons.Default.CreditCard,
                        onClick = {
                            /*externalRouter.navigateTo(
                                MainNavRoute.Create.route + "?${ARGUMENT_CREATE_KEY}=${TYPE_PROJECT_DONATE}"
                            )*/
                        }
                    )
                    TypeProjectItem(
                        TYPE_PROJECT_TEAM_TEXT,
                        Icons.Default.People,
                        onClick = {
                            /*externalRouter.navigateTo(
                                MainNavRoute.Create.route + "?${ARGUMENT_CREATE_KEY}=${TYPE_PROJECT_TEAM}"
                            )*/
                        }
                    )
                }
            }
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.White,
        sheetElevation = 10.dp,
        sheetShape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
    ) {
        HomeScreen(
            router = externalRouter,
            onClickCreate = {
                coroutineScope.launch {
                    sheetState.show()
                }
            }
        )
    }
}