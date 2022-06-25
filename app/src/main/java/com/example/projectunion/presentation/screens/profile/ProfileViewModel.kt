package com.example.projectunion.presentation.screens.profile

import androidx.lifecycle.*
import com.example.projectunion.common.Constants.ARGUMENT_PROFILE_KEY
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserProfile
import com.example.projectunion.domain.use_case.GetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
	private val getUserByIdUseCase: GetUserByIdUseCase,
	private val savedStateHandle: SavedStateHandle
): ViewModel() {

	private val _state = MutableLiveData<Response<UserProfile?>>()
	val state: LiveData<Response<UserProfile?>> get() = _state

	init {
		getProfileData()
	}

	private fun getProfileData() {
		savedStateHandle.get<String>(ARGUMENT_PROFILE_KEY)?.let { userID ->
			viewModelScope.launch {
				getUserByIdUseCase(userID).collect { response ->
					_state.postValue(response)
				}
			}
		}
	}
}