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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.common.Constants.BUTTON_LOGIN
import com.example.novalinea.common.Constants.ERROR_BY_CHECK_EMAIL
import com.example.novalinea.common.Constants.ERROR_EMAIL_IS_USED
import com.example.novalinea.common.Constants.TITLE_ALREADY_CREATE_ACCOUNT
import com.example.novalinea.common.Constants.TITLE_CREATE_ACCOUNT
import com.example.novalinea.domain.model.Response
import com.example.novalinea.presentation.components.button_action.ButtonNextStep
import com.example.novalinea.presentation.components.email_field.Email
import com.example.novalinea.presentation.components.email_field.EmailState
import com.example.novalinea.presentation.components.error_field.ErrorField
import com.example.novalinea.presentation.components.name_field.Name
import com.example.novalinea.presentation.components.name_field.NameState
import com.example.novalinea.presentation.components.password_field.Password
import com.example.novalinea.presentation.components.password_field.PasswordState
import com.example.novalinea.presentation.components.text_button_action.TextButtonAction
import com.example.novalinea.presentation.ui.theme.OpenSans

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainData(
	name: NameState,
	onChangeName: (String) -> Unit,
	email: EmailState,
	onChangeEmail: (String) -> Unit,
	password: PasswordState,
	onChangePassword: (String) -> Unit,
	enabled: Boolean,
	focusManager: FocusManager,
	stateCheckEmail: Response<Boolean?>,
	onClickLogin: () -> Unit,
	onClickNext: () -> Unit
) {

	Scaffold {
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
					modifier = Modifier.padding(start = 30.dp),
					text = TITLE_CREATE_ACCOUNT,
					style = MaterialTheme.typography.h5
				)
			}

			Spacer(modifier = Modifier.height(20.dp))
			Column(
				modifier = Modifier.padding(horizontal = 30.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Name(
					name = name.text,
					error = name.error,
					focusManager = focusManager,
					onChangeName = onChangeName
				)
				Spacer(modifier = Modifier.height(10.dp))

				Email(
					email = email.text,
					error = email.error,
					focusManager = focusManager,
					onChangeEmail = onChangeEmail
				)
				Spacer(modifier = Modifier.height(10.dp))

				Password(
					password = password.text,
					error = password.error,
					focusManager = focusManager,
					onChangePassword = onChangePassword
				)

				if (stateCheckEmail is Response.Error) {
					Spacer(modifier = Modifier.height(5.dp))
					ErrorField(error = ERROR_BY_CHECK_EMAIL)
				}
				else if (stateCheckEmail is Response.Success && stateCheckEmail.data == false) {
					Spacer(modifier = Modifier.height(5.dp))
					ErrorField(error = ERROR_EMAIL_IS_USED)
				}
				Spacer(modifier = Modifier.height(65.dp))
			}
		}

		Box(
			modifier = Modifier
				.padding(start = 20.dp, bottom = 15.dp)
				.fillMaxSize(),
			contentAlignment = Alignment.BottomStart
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					text = TITLE_ALREADY_CREATE_ACCOUNT,
					style = TextStyle(
						fontFamily = OpenSans,
						fontSize = 15.sp,
						fontWeight = FontWeight.W400
					),
					color = Color.DarkGray
				)

				TextButtonAction(title = BUTTON_LOGIN) {
					onClickLogin()
				}
			}
		}

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
}