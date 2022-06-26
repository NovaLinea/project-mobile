package com.example.projectunion.presentation.screens.project

import android.util.Log
import androidx.lifecycle.*
import com.example.projectunion.common.Constants.ARGUMENT_PROJECT_ID_KEY
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.domain.model.ProjectOpen
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.use_case.GetProjectByIdUseCase
import com.example.projectunion.domain.use_case.IncrementViewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val getProjectByIdUseCase: GetProjectByIdUseCase,
    private val incrementViewUseCase: IncrementViewUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _stateProject = MutableLiveData<Response<ProjectOpen?>>()
    val stateProject: LiveData<Response<ProjectOpen?>> get() = _stateProject

    init {
        savedStateHandle.get<String>(ARGUMENT_PROJECT_ID_KEY)?.let { projectID ->
            if (projectID != "-1") {
                getProjectById(projectID)
                incrementView(projectID)
            }
        }
    }

    private fun getProjectById(projectID: String) {
        viewModelScope.launch {
            getProjectByIdUseCase(projectID).collect { response ->
                _stateProject.postValue(response)
            }
        }
    }

    private fun incrementView(projectID: String) {
        viewModelScope.launch {
            incrementViewUseCase(projectID).collect { response ->
                if (response is Response.Error) {
                    Log.d(TAG, response.message)
                }
            }
        }
    }
}