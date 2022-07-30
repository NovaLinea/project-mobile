package com.example.novalinea.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.UserProfile
import com.example.novalinea.domain.use_case.CheckAuthorizedUseCase
import com.example.novalinea.domain.use_case.GetAuthCurrentUserUseCase
import com.example.novalinea.domain.use_case.GetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
	private val checkAuthorizedUseCase: CheckAuthorizedUseCase,
	private val getAuthCurrentUserUseCase: GetAuthCurrentUserUseCase,
	private val getUserByIdUseCase: GetUserByIdUseCase
): ViewModel() {

	private val isAuth get() = checkAuthorizedUseCase()

	private val _isLoading = MutableStateFlow(true)
	val isLoading = _isLoading.asStateFlow()

	fun getDataUser() {
		if (isAuth) {
			viewModelScope.launch {
				val id = getAuthCurrentUserUseCase()?.uid.toString()

				getUserByIdUseCase(id).collect { response ->
					if (response is Response.Success) {
						response.data?.let { user ->
							USER = user
							_isLoading.value = false
						}
					}
					else if (response is Response.Error)
						USER = UserProfile()
				}
			}
		}
		else
			USER = UserProfile()
	}
}