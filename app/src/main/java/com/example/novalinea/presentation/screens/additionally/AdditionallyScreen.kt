package com.example.novalinea.presentation.screens.additionally

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.novalinea.common.Constants.ADDITIONALLY_SCREEN
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.ERROR_BY_LOGOUT
import com.example.novalinea.common.Constants.BUTTON_LOGOUT
import com.example.novalinea.common.Constants.MAIN_ROUTE
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.common.Constants.TEXT_LOGOUT_ACCOUNT
import com.example.novalinea.common.Constants.TITLE_LOGOUT_ACCOUNT
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.UserProfile
import com.example.novalinea.presentation.components.loader.Loader
import com.example.novalinea.presentation.components.modal.Modal
import com.example.novalinea.presentation.components.text_button_action.TextButtonAction
import com.example.novalinea.presentation.components.top_bar.TopBar
import com.example.novalinea.presentation.navigation.BottomNavRoute
import com.example.novalinea.presentation.navigation.Router
import com.example.novalinea.presentation.screens.additionally.components.AdditionallyItem
import com.example.novalinea.presentation.screens.additionally.components.AdditionallyModel
import com.example.novalinea.presentation.screens.additionally.components.ProfileUser
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun AdditionallyScreen(
	externalRouter: Router,
	viewModel: AdditionallyViewModel = hiltViewModel(),
) {
	val state = viewModel.state.observeAsState(Response.Success(false)).value

	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope()

	val openDialog = remember { mutableStateOf(false) }

	val items = listOf(
		AdditionallyModel.Theme,
		AdditionallyModel.AboutApp
	)

	Scaffold(
		topBar = { TopBar(title = ADDITIONALLY_SCREEN) },
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
		when(state) {
			is Response.Loading -> Loader()
			is Response.Error -> {
				Log.d(TAG, state.message)
				scope.launch {
					scaffoldState.snackbarHostState.showSnackbar(ERROR_BY_LOGOUT)
				}
			}
			is Response.Success -> {
				if (state.data) {
					LaunchedEffect(state.data) {
						externalRouter.routeTo(MAIN_ROUTE)
						USER = UserProfile()
					}
				}
			}
		}

		Column(
			modifier = Modifier.fillMaxWidth(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			ProfileUser(
				name = USER.name,
				photo  = USER.photo,
				onClick = {
					USER.id?.let { id ->
						externalRouter.routeTo(
							BottomNavRoute.Profile.route + "?${ARGUMENT_USER_ID_KEY}=${id}"
						)
					}
				}
			)
			
			Spacer(modifier = Modifier.height(10.dp))

			items.forEach { item ->
				AdditionallyItem(title = item.title, icon = item.icon) {
					externalRouter.routeTo(item.screen_route)
					//navController.navigate(item.screen_route)
				}
			}

			TextButtonAction(
				title = BUTTON_LOGOUT,
				color = Color.Red,
				onClick = {
					openDialog.value = true
				}
			)
		}

		Modal(
			openDialog = openDialog,
			title = TITLE_LOGOUT_ACCOUNT,
			text = TEXT_LOGOUT_ACCOUNT,
			confirmButton = BUTTON_LOGOUT,
			onClickTrue = {
				viewModel.logout()
			}
		)
	}
}