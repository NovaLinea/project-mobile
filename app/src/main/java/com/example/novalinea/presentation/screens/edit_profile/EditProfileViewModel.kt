package com.example.novalinea.presentation.screens.edit_profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.novalinea.common.Constants.ARGUMENT_USER_DESCRIPTION_KEY
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.ARGUMENT_USER_NAME_KEY
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.UserEdit
import com.example.novalinea.domain.use_case.EditProfileUseCase
import com.example.novalinea.presentation.components.name_field.NameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val editProfileUseCase: EditProfileUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val name by lazy { NameState() }
    val description by lazy { mutableStateOf("") }
    val photo by lazy { mutableStateOf("") }

    private val _state = MutableLiveData<Response<Boolean>>()
    val state: LiveData<Response<Boolean>> get() = _state

    init {
        savedStateHandle.get<String>(ARGUMENT_USER_NAME_KEY)?.let {
            name.text = it
        }
        savedStateHandle.get<String>(ARGUMENT_USER_DESCRIPTION_KEY)?.let {
            description.value = it
        }
    }

    fun editProfile() {
        savedStateHandle.get<String>(ARGUMENT_USER_ID_KEY)?.let { id ->
            viewModelScope.launch {
                val user = UserEdit(id = id, name = name.text, description = description.value)
                editProfileUseCase(user).collect { response ->
                    _state.postValue(response)
                }
            }
        }
    }
}