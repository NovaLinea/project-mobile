package com.example.projectunion.presentation.screens.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserProfile
import com.example.projectunion.domain.use_case.GetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
	private val getUserByIdUseCase: GetUserByIdUseCase
): ViewModel() {

	private val _state = MutableLiveData<Response<UserProfile?>>()
	val state: LiveData<Response<UserProfile?>> get() = _state

	init {
		getProfileData()
	}

	private fun getProfileData() {
		viewModelScope.launch {
			getUserByIdUseCase("id").collect { response ->
				_state.postValue(response)
			}
		}
	}
}