package com.example.projectunion.presentation.screens.profile

import androidx.lifecycle.*
import com.example.projectunion.common.Constants.ARGUMENT_PROFILE_KEY
import com.example.projectunion.domain.model.ProjectTape
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserProfile
import com.example.projectunion.domain.use_case.GetProjectsUserUseCase
import com.example.projectunion.domain.use_case.GetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
	private val getUserByIdUseCase: GetUserByIdUseCase,
	private val getProjectsUserUseCase: GetProjectsUserUseCase,
	private val savedStateHandle: SavedStateHandle
): ViewModel() {

	private val _stateProfile = MutableLiveData<Response<UserProfile?>>()
	val stateProfile: LiveData<Response<UserProfile?>> get() = _stateProfile

	private val _stateProjects = MutableLiveData<Response<List<ProjectTape>>>()
	val stateProjects: LiveData<Response<List<ProjectTape>>> get() = _stateProjects

	init {
		getProfileData()
		getProjects()
	}

	private fun getProfileData() {
		savedStateHandle.get<String>(ARGUMENT_PROFILE_KEY)?.let { userID ->
			viewModelScope.launch {
				getUserByIdUseCase(userID).collect { response ->
					_stateProfile.postValue(response)
				}
			}
		}
	}

	private fun getProjects() {
		savedStateHandle.get<String>(ARGUMENT_PROFILE_KEY)?.let { userID ->
			viewModelScope.launch {
				getProjectsUserUseCase(userID).collect { response ->
					_stateProjects.postValue(response)
				}
			}
		}
	}
}