package com.example.novalinea.presentation.screens.project

import android.util.Log
import androidx.lifecycle.*
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_ID_KEY
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.common.Constants.TEXT_APPLICATION_BUY_PROJECT
import com.example.novalinea.common.Constants.TEXT_BUY_YOURSELF_PROJECT
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.*
import com.example.novalinea.domain.use_case.GetProjectByIdUseCase
import com.example.novalinea.domain.use_case.IncrementViewUseCase
import com.example.novalinea.domain.use_case.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val getProjectByIdUseCase: GetProjectByIdUseCase,
    private val incrementViewUseCase: IncrementViewUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _stateProject = MutableLiveData<Response<ProjectOpen?>>()
    val stateProject: LiveData<Response<ProjectOpen?>> get() = _stateProject

    private val _stateSend = MutableLiveData<Response<Boolean>>()
    val stateSend: LiveData<Response<Boolean>> get() = _stateSend

    init {
        savedStateHandle.get<String>(ARGUMENT_PROJECT_ID_KEY)?.let { projectID ->
            incrementView(projectID = projectID)
            getProjectById(projectID = projectID)
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

    fun sendMessage(
        toID: String?,
        projectID: String?,
        projectTitle: String?,
        projectPrice: Int?
    ) {
        toID?.let { toID ->
            viewModelScope.launch {
                USER.id?.let { fromID ->
                    if (toID != fromID) {
                        val messageSend = MessageSend(
                            text = TEXT_APPLICATION_BUY_PROJECT,
                            from = fromID,
                            to = toID,
                            type = TypesMessage.BUY_PROJECT,
                            project_id = projectID,
                            project_title = projectTitle,
                            project_price = projectPrice
                        )
                        sendMessageUseCase(messageSend).collect { response ->
                            _stateSend.postValue(response)
                        }
                    }
                    else {
                        _stateSend.postValue(Response.Error(TEXT_BUY_YOURSELF_PROJECT))
                    }
                }
            }
        }
    }
}