package com.example.projectunion.presentation.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectunion.data.repository.AuthRepositoryImpl
import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.use_case.LoginByEmailUseCase

class LoginViewModel : ViewModel() {
	private val authRepository by lazy(LazyThreadSafetyMode.NONE) {
		AuthRepositoryImpl()
	}
	private val loginByEmailUseCase by lazy(LazyThreadSafetyMode.NONE) {
		LoginByEmailUseCase(authRepository)
	}

	private val _state = MutableLiveData<LoginState>()
	val state: LiveData<LoginState> = _state

	fun loginByEmail(email: String, password: String) {
		val status = loginByEmailUseCase(UserLogin(email = email, password = password))
		_state.value = LoginState(auth = status)
	}
}