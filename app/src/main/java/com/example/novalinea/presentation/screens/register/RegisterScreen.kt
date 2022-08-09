package com.example.novalinea.presentation.screens.register

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.novalinea.common.Constants.ARGUMENT_USER_EMAIL_KEY
import com.example.novalinea.common.Constants.AUTHENTICATION_ROUTE
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.Response.*
import com.example.novalinea.domain.model.StepsRegister
import com.example.novalinea.presentation.components.top_bar.StepsTopBar
import com.example.novalinea.presentation.navigation.AuthNavRoute
import com.example.novalinea.presentation.navigation.PresentNested
import com.example.novalinea.presentation.screens.register.components.steps.MainData
import com.example.novalinea.presentation.screens.register.components.steps.Photo
import com.example.novalinea.presentation.screens.register.components.steps.UserName

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
	navController: NavController,
	viewModel: RegisterViewModel = hiltViewModel()
) {
	val stateCheckEmail = viewModel.stateCheckEmail.observeAsState(Success(null)).value
	val stateCheckUserName = viewModel.stateCheckUserName.observeAsState(Success(null)).value
	val stateRegister = viewModel.stateRegister.observeAsState(Success(false)).value

	val focusManager = LocalFocusManager.current

	val stateSteps = remember {
		mutableStateListOf(true, false, false)
	}
	var step = remember {
		mutableStateOf(StepsRegister.MAIN_DATA)
	}

	Scaffold(
		topBar = {
			StepsTopBar(
				stateSteps = stateSteps,
				isShowBack = step.value != StepsRegister.MAIN_DATA,
				onClickBack = {
					when(step.value) {
						StepsRegister.USERNAME -> {
							step.value = StepsRegister.MAIN_DATA
							stateSteps[1] = false
						}
						StepsRegister.PHOTO -> {
							step.value = StepsRegister.USERNAME
							stateSteps[2] = false
						}
					}
				},
				onClickClose = {
					navController.popBackStack()
				}
			)
		}
	) {
		if (stateCheckEmail is Success && stateCheckEmail.data == true) {
			stateSteps[1] = true
			step.value = StepsRegister.USERNAME
		}

		if (stateCheckUserName is Success && stateCheckUserName.data == true) {
			stateSteps[2] = true
			step.value = StepsRegister.PHOTO
		}

		when(stateRegister) {
			is Loading -> Log.d(TAG, "Loading")
			is Error -> Log.d(TAG, stateRegister.message)
			is Success -> {
				if (stateRegister.data) {
					LaunchedEffect(stateRegister.data) {
						navController.navigate(
							AuthNavRoute.VerifyEmail.route
									+ "?${ARGUMENT_USER_EMAIL_KEY}=${viewModel.email.text}"
						)
					}
				}
			}
		}

		when(step.value) {
			StepsRegister.MAIN_DATA -> {
				PresentNested {
					MainData(
						name = viewModel.name,
						onChangeName = {
							viewModel.name.text = it
							viewModel.name.validate()
						},
						email = viewModel.email,
						onChangeEmail = {
							viewModel.email.text = it
							viewModel.email.validate()
						},
						password = viewModel.password,
						onChangePassword = {
							viewModel.password.text = it
							viewModel.password.validate()
						},
						enabled = stateCheckEmail != Loading
								&& viewModel.name.isValidText()
								&& viewModel.email.isValidText()
								&& viewModel.password.isValidText(),
						focusManager = focusManager,
						stateCheckEmail = stateCheckEmail,
						onClickLogin = {
							navController.popBackStack()
							navController.navigate(AUTHENTICATION_ROUTE)
						},
						onClickNext = {
							viewModel.checkEmail()
						}
					)
				}
			}

			StepsRegister.USERNAME -> {
				PresentNested {
					UserName(
						username = viewModel.username,
						onChangeUserName = {
							viewModel.username.text = it
							viewModel.username.validate()
						},
						enabled = stateCheckUserName != Loading
								&& viewModel.username.isValidText(),
						focusManager = focusManager,
						stateCheckUserName = stateCheckUserName,
						onClickNext = {
							viewModel.checkUserName()
						}
					)
				}
			}

			StepsRegister.PHOTO -> {
				PresentNested {
					Photo(

					)
				}
			}
		}
	}
}