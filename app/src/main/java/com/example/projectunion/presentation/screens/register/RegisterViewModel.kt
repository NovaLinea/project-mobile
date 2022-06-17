package com.example.projectunion.presentation.screens.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserRegister
import com.example.projectunion.domain.use_case.RegisterByEmailUseCase
import com.example.projectunion.presentation.components.email_field.EmailState
import com.example.projectunion.presentation.components.name_field.NameState
import com.example.projectunion.presentation.components.password_field.PasswordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
	private val registerByEmailUseCase: RegisterByEmailUseCase
) : ViewModel() {
	val name by lazy { NameState() }
	val email by lazy { EmailState() }
	val password by lazy { PasswordState() }

	private val _state = MutableLiveData<Response<Boolean>>()
	val state: LiveData<Response<Boolean>> get() = _state

	fun registerByEmail() {
		viewModelScope.launch {
			val user = UserRegister(name = name.text, email = email.text, password = password.text)
			registerByEmailUseCase(user).collect { response ->
				_state.postValue(response)
			}
		}
	}
}
