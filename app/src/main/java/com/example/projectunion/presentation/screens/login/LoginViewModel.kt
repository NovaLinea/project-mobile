package com.example.projectunion.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.Response.Success
import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.use_case.LoginByEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val loginByEmailUseCase: LoginByEmailUseCase
) : ViewModel() {
	private val _state = MutableLiveData<LoginState>()
	val state: LiveData<LoginState> = _state

	//private val _state = mutableStateOf<Response<Boolean>>(Success(false))
	//val state: State<Response<Boolean>> = _state

	fun loginByEmail(email: String, password: String) {
		val status = loginByEmailUseCase(UserLogin(email = email, password = password))
		//_state.value = LoginState(auth = status)
	}

	/*fun loginByEmail(email: String, password: String) {
		viewModelScope.launch {
			loginByEmailUseCase(UserLogin(email = email, password = password)).collect { response ->
				_state.value = response
			}
		}
	}*/
}