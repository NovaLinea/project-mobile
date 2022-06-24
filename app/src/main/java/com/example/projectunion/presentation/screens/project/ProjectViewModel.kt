package com.example.projectunion.presentation.screens.project

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectunion.domain.model.ProjectOpen
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.use_case.GetProjectByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val getProjectByIdUseCase: GetProjectByIdUseCase
): ViewModel() {

    private val _state = MutableLiveData<Response<ProjectOpen>>()
    val state: LiveData<Response<ProjectOpen>> get() = _state

    private var _userID = mutableStateOf<String>("")
    var userID: State<String> = _userID

    init {
        getProjectById()
    }

    private fun getProjectById() {
        viewModelScope.launch {
            getProjectByIdUseCase("id").collect { response ->
                _state.postValue(response)
            }
        }
    }
}