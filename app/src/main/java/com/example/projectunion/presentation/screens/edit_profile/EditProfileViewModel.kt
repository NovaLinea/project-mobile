package com.example.projectunion.presentation.screens.edit_profile

import androidx.lifecycle.*
import com.example.projectunion.common.Constants.ARGUMENT_PROFILE_ID_KEY
import com.example.projectunion.domain.model.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableLiveData<Response<Boolean>>()
    val state: LiveData<Response<Boolean>> get() = _state

    fun editProfile() {
        savedStateHandle.get<String>(ARGUMENT_PROFILE_ID_KEY)?.let { id ->
            if (id != "-1") {

            }
        }
    }
}