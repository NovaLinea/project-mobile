package com.example.novalinea.presentation.screens.profile

import android.net.Uri
import androidx.lifecycle.*
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.ProjectTape
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.UserProfile
import com.example.novalinea.domain.use_case.GetProjectsUserUseCase
import com.example.novalinea.domain.use_case.GetUserByIdUseCase
import com.example.novalinea.domain.use_case.LogoutUseCase
import com.example.novalinea.domain.use_case.UploadPhotoUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
	private val getUserByIdUseCase: GetUserByIdUseCase,
	private val getProjectsUserUseCase: GetProjectsUserUseCase,
	private val uploadPhotoUserUseCase: UploadPhotoUserUseCase,
	private val logoutUseCase: LogoutUseCase,
	private val savedStateHandle: SavedStateHandle
): ViewModel() {

	private val _stateProfile = MutableLiveData<Response<UserProfile?>>()
	val stateProfile: LiveData<Response<UserProfile?>> get() = _stateProfile

	private val _stateProjects = MutableLiveData<Response<List<ProjectTape>>>()
	val stateProjects: LiveData<Response<List<ProjectTape>>> get() = _stateProjects

	private val _statePhoto = MutableLiveData<Response<String>>()
	val statePhoto: LiveData<Response<String>> get() = _statePhoto

	private val _photoProfile = MutableLiveData<String?>()
	val photoProfile: LiveData<String?> get() = _photoProfile

	//private val _stateLogout = MutableLiveData<Response<Boolean>>()
	//val stateLogout: LiveData<Response<Boolean>> get() = _stateLogout

	init {
		savedStateHandle.get<String>(ARGUMENT_USER_ID_KEY)?.let { userID ->
			getProfileData(userID)
			getProjects(userID)
		}
	}

	fun getProfileData(userID: String) {
		viewModelScope.launch {
			getUserByIdUseCase(userID).collect { response ->
				if (response is Response.Success)
					_photoProfile.postValue(response.data?.photo)

				_stateProfile.postValue(response)
			}
		}
	}

	fun getProjects(userID: String) {
		viewModelScope.launch {
			getProjectsUserUseCase(userID).collect { response ->
				_stateProjects.postValue(response)
			}
		}
	}

	fun updatePhoto(photoUri: Uri) {
		savedStateHandle.get<String>(ARGUMENT_USER_ID_KEY)?.let { userID ->
			viewModelScope.launch {
				uploadPhotoUserUseCase(photoUri, userID).collect { response ->
					_statePhoto.postValue(response)
					if (response is Response.Success) {
						_photoProfile.postValue(response.data)
						USER.photo = response.data
					}
				}
			}
		}
	}

	/*fun logout() {
		viewModelScope.launch {
			logoutUseCase().collect { response ->
				_stateLogout.postValue(response)
			}
		}
	}*/
}