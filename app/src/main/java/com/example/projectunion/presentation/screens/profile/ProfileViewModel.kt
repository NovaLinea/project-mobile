package com.example.projectunion.presentation.screens.profile

import android.net.Uri
import androidx.lifecycle.*
import com.example.projectunion.common.Constants.ARGUMENT_PROFILE_KEY
import com.example.projectunion.domain.model.ProjectTape
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserProfile
import com.example.projectunion.domain.use_case.GetProjectsUserUseCase
import com.example.projectunion.domain.use_case.GetUserByIdUseCase
import com.example.projectunion.domain.use_case.UploadPhotoUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
	private val getUserByIdUseCase: GetUserByIdUseCase,
	private val getProjectsUserUseCase: GetProjectsUserUseCase,
	private val uploadPhotoUserUseCase: UploadPhotoUserUseCase,
	private val savedStateHandle: SavedStateHandle
): ViewModel() {

	private val _stateProfile = MutableLiveData<Response<UserProfile?>>()
	val stateProfile: LiveData<Response<UserProfile?>> get() = _stateProfile

	private val _stateProjects = MutableLiveData<Response<List<ProjectTape>>>()
	val stateProjects: LiveData<Response<List<ProjectTape>>> get() = _stateProjects

	private val _statePhoto = MutableLiveData<Response<Boolean>>()
	val statePhoto: LiveData<Response<Boolean>> get() = _statePhoto

	val photoUri = MutableLiveData<Uri?>()

	init {
		savedStateHandle.get<String>(ARGUMENT_PROFILE_KEY)?.let { userID ->
			if (userID != "-1") {
				getProfileData(userID)
				getProjects(userID)
			}
		}
	}

	private fun getProfileData(userID: String) {
		viewModelScope.launch {
			getUserByIdUseCase(userID).collect { response ->
				_stateProfile.postValue(response)
			}
		}
	}

	private fun getProjects(userID: String) {
		viewModelScope.launch {
			getProjectsUserUseCase(userID).collect { response ->
				_stateProjects.postValue(response)
			}
		}
	}

	fun updatePhoto() {
		savedStateHandle.get<String>(ARGUMENT_PROFILE_KEY)?.let { userID ->
			if (userID != "-1") {
				viewModelScope.launch {
					photoUri.value?.let { photo ->
						uploadPhotoUserUseCase(photo, userID).collect { response ->
							_statePhoto.postValue(response)
						}
					}
				}
			}
		}
	}
}