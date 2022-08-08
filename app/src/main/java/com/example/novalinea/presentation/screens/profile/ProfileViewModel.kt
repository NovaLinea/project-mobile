package com.example.novalinea.presentation.screens.profile

import android.net.Uri
import androidx.lifecycle.*
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.UserProfile
import com.example.novalinea.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
	private val getUserByIdUseCase: GetUserByIdUseCase,
	private val uploadPhotoUserUseCase: UploadPhotoUserUseCase,
	private val deletePhotoUserUseCase: DeletePhotoUserUseCase,
	private val logoutUseCase: LogoutUseCase,
	getProjectsUserUseCase: GetProjectsUserUseCase,
	savedStateHandle: SavedStateHandle
): ViewModel() {

	val projects = savedStateHandle.get<String>(ARGUMENT_USER_ID_KEY)?.let { userID ->
		getProjectsUserUseCase(
			userID = userID,
			viewModelScope = viewModelScope
		)
	}

	private val _stateProfile = MutableLiveData<Response<UserProfile?>>()
	val stateProfile: LiveData<Response<UserProfile?>> get() = _stateProfile

	private val _statePhoto = MutableLiveData<Response<String>>()
	val statePhoto: LiveData<Response<String>> get() = _statePhoto

	private val _photoProfile = MutableLiveData<String?>()
	val photoProfile: LiveData<String?> get() = _photoProfile

	private val _stateLogout = MutableLiveData<Response<Boolean>>()
	val stateLogout: LiveData<Response<Boolean>> get() = _stateLogout

	init {
		savedStateHandle.get<String>(ARGUMENT_USER_ID_KEY)?.let { userID ->
			getProfileData(userID)
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

	fun updatePhoto(photoUri: Uri) {
		viewModelScope.launch {
			USER.id?.let { userID ->
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

	fun deletePhoto() {
		USER.id?.let { userID ->
			viewModelScope.launch {
				deletePhotoUserUseCase(userID).collect { response ->
					if (response is Response.Success) {
						_photoProfile.postValue(null)
						USER.photo = null
					}
				}
			}
		}
	}

	fun logout() {
		viewModelScope.launch {
			logoutUseCase().collect { response ->
				_stateLogout.postValue(response)
			}
		}
	}
}