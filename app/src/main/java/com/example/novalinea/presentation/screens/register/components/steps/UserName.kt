package com.example.novalinea.presentation.screens.register.components.steps

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import com.example.novalinea.common.Constants.ERROR_BY_CHECK_USERNAME
import com.example.novalinea.common.Constants.ERROR_USERNAME_IS_USED
import com.example.novalinea.common.Constants.TITLE_USERNAME_ACCOUNT
import com.example.novalinea.domain.model.Response
import com.example.novalinea.presentation.components.button_action.ButtonNextStep
import com.example.novalinea.presentation.components.error_field.ErrorField
import com.example.novalinea.presentation.components.username_field.UserNameState
import com.example.novalinea.presentation.components.username_field.UserName

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserName(
	username: UserNameState,
	onChangeUserName: (String) -> Unit,
	enabled: Boolean,
	focusManager: FocusManager,
	stateCheckUserName: Response<Boolean?>,
	onClickNext: () -> Unit
) {
	Scaffold(
		bottomBar = {
			Box(
				modifier = Modifier
					.padding(end = 15.dp, bottom = 15.dp)
					.fillMaxSize(),
				contentAlignment = Alignment.BottomEnd
			) {
				ButtonNextStep(
					enabled = enabled,
					onClickNext = onClickNext
				)
			}
		}
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.verticalScroll(rememberScrollState()),
		) {
			Spacer(modifier = Modifier.height(50.dp))
			Box(
				contentAlignment = Alignment.CenterStart
			) {
				Text(
					modifier = Modifier.padding(horizontal = 30.dp),
					text = TITLE_USERNAME_ACCOUNT,
					style = MaterialTheme.typography.h5
				)
			}

			Spacer(modifier = Modifier.height(20.dp))
			Column(
				modifier = Modifier.padding(horizontal = 30.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				UserName(
					username = username.text,
					error = username.error,
					focusManager = focusManager,
					onChangeUserName = onChangeUserName
				)

				if (stateCheckUserName is Response.Error) {
					Spacer(modifier = Modifier.height(5.dp))
					ErrorField(error = ERROR_BY_CHECK_USERNAME)
				}
				else if (stateCheckUserName is Response.Success && stateCheckUserName.data == false) {
					Spacer(modifier = Modifier.height(5.dp))
					ErrorField(error = ERROR_USERNAME_IS_USED)
				}
			}
		}
	}
}