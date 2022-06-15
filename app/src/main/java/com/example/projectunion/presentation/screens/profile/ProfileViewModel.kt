package com.example.projectunion.presentation.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.use_case.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
	private val logoutUseCase: LogoutUseCase
): ViewModel() {

	private val _state = mutableStateOf<Response<Boolean>>(Response.Success(false))
	val state: State<Response<Boolean>> get() = _state

	fun logout() {
		viewModelScope.launch {
			logoutUseCase().collect { response ->
				_state.value = response
			}
		}
	}
}