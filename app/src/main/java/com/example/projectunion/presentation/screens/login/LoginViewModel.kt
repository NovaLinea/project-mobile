package com.example.projectunion.presentation.screens.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.Response.*
import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.use_case.LoginByEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val loginByEmailUseCase: LoginByEmailUseCase
) : ViewModel() {
	val email by lazy { MutableStateFlow<String>("") }
	val password by lazy { MutableStateFlow<String>("") }

	private val _state = mutableStateOf<Response<Boolean>>(Success(false))
	val state: State<Response<Boolean>> get() = _state

	fun loginByEmail() {
		viewModelScope.launch {
			val user = UserLogin(email = email.value, password = password.value)
			loginByEmailUseCase(user).collect { response ->
				_state.value = response
			}
		}
	}
}
