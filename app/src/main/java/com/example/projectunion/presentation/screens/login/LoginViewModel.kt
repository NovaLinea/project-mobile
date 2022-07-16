package com.example.projectunion.presentation.screens.login

import androidx.lifecycle.*
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.use_case.LoginByEmailUseCase
import com.example.projectunion.presentation.components.email_field.EmailState
import com.example.projectunion.presentation.components.password_field.PasswordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val loginByEmailUseCase: LoginByEmailUseCase
) : ViewModel() {

	val email by lazy { EmailState() }
	val password by lazy { PasswordState() }

	private val _state = MutableLiveData<Response<Boolean>>()
	val state: LiveData<Response<Boolean>> get() = _state

	fun loginByEmail() {
		viewModelScope.launch {
			val user = UserLogin(email = email.text, password = password.text)
			loginByEmailUseCase(user).collect { response ->
				_state.postValue(response)
			}
		}
	}
}
