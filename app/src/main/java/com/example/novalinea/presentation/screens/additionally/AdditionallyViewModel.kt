package com.example.novalinea.presentation.screens.additionally

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.use_case.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdditionallyViewModel @Inject constructor(
	private val logoutUseCase: LogoutUseCase
): ViewModel() {

	private val _state = MutableLiveData<Response<Boolean>>(Response.Success(false))
	val state: LiveData<Response<Boolean>> get() = _state

	fun logout() {
		viewModelScope.launch {
			logoutUseCase().collect { response ->
				_state.postValue(response)
			}
		}
	}
}