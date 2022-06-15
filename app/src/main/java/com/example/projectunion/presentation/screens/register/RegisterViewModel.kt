package com.example.projectunion.presentation.screens.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserRegister
import com.example.projectunion.domain.use_case.RegisterByEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
	private val registerByEmailUseCase: RegisterByEmailUseCase
) : ViewModel() {
	val name by lazy { MutableStateFlow<String>("") }
	val email by lazy { MutableStateFlow<String>("") }
	val password by lazy { MutableStateFlow<String>("") }

	private val _state = mutableStateOf<Response<Boolean>>(Response.Success(false))
	val state: State<Response<Boolean>> get() = _state

	fun registerByEmail() {
		viewModelScope.launch {
			val user = UserRegister(name = name.value, email = email.value, password = password.value)
			registerByEmailUseCase(user).collect { response ->
				_state.value = response
			}
		}
	}
}
