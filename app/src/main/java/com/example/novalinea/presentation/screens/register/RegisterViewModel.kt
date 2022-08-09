package com.example.novalinea.presentation.screens.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.UserRegister
import com.example.novalinea.domain.use_case.CheckEmailByRegisterUseCase
import com.example.novalinea.domain.use_case.CheckUserNameByRegisterUseCase
import com.example.novalinea.domain.use_case.RegisterByEmailUseCase
import com.example.novalinea.presentation.components.email_field.EmailState
import com.example.novalinea.presentation.components.name_field.NameState
import com.example.novalinea.presentation.components.password_field.PasswordState
import com.example.novalinea.presentation.components.username_field.UserNameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
	private val checkEmailByRegisterUseCase: CheckEmailByRegisterUseCase,
	private val checkUserNameByRegisterUseCase: CheckUserNameByRegisterUseCase,
	private val registerByEmailUseCase: RegisterByEmailUseCase
) : ViewModel() {

	val username by lazy { UserNameState() }
	val name by lazy { NameState() }
	val email by lazy { EmailState() }
	val password by lazy { PasswordState() }

	private val _stateCheckEmail = MutableLiveData<Response<Boolean?>>()
	val stateCheckEmail: LiveData<Response<Boolean?>> get() = _stateCheckEmail

	private val _stateCheckUserName = MutableLiveData<Response<Boolean?>>()
	val stateCheckUserName: LiveData<Response<Boolean?>> get() = _stateCheckUserName

	private val _stateRegister = MutableLiveData<Response<Boolean>>()
	val stateRegister: LiveData<Response<Boolean>> get() = _stateRegister

	fun checkEmail() {
		viewModelScope.launch {
			checkEmailByRegisterUseCase(email.text).collect { response ->
				_stateCheckEmail.postValue(response)
			}
		}
	}

	fun checkUserName() {
		viewModelScope.launch {
			checkUserNameByRegisterUseCase(username.text).collect { response ->
				_stateCheckUserName.postValue(response)
			}
		}
	}

	fun registerByEmail() {
		viewModelScope.launch {
			val user = UserRegister(
				name = name.text,
				username = username.text,
				email = email.text,
				password = password.text
			)
			registerByEmailUseCase(user).collect { response ->
				_stateRegister.postValue(response)
			}
		}
	}
}
