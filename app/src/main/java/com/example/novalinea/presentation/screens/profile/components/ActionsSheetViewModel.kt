package com.example.novalinea.presentation.screens.profile.components

import androidx.lifecycle.*
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.use_case.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActionsSheetViewModel @Inject constructor(
	private val logoutUseCase: LogoutUseCase
): ViewModel() {

	private val _stateLogout = MutableLiveData<Response<Boolean>>()
	val stateLogout: LiveData<Response<Boolean>> get() = _stateLogout

	fun logout() {
		viewModelScope.launch {
			logoutUseCase().collect { response ->
				_stateLogout.postValue(response)
			}
		}
	}
}