package com.example.projectunion.presentation.screens.project

import androidx.lifecycle.*
import com.example.projectunion.common.Constants.ARGUMENT_PROJECT_KEY
import com.example.projectunion.domain.model.ProjectOpen
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.use_case.GetProjectByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val getProjectByIdUseCase: GetProjectByIdUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableLiveData<Response<ProjectOpen?>>()
    val state: LiveData<Response<ProjectOpen?>> get() = _state

    init {
        getProjectById()
    }

    private fun getProjectById() {
        savedStateHandle.get<String>(ARGUMENT_PROJECT_KEY)?.let { projectID ->
            if (projectID != "-1") {
                viewModelScope.launch {
                    getProjectByIdUseCase(projectID).collect { response ->
                        _state.postValue(response)
                    }
                }
            }
        }
    }
}