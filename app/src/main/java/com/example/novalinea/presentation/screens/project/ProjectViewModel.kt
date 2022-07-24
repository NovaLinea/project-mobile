package com.example.novalinea.presentation.screens.project

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.*
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_DATA
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.BUY_PROJECT_MESSAGE
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.common.Constants.TEXT_BUY_YOURSELF_PROJECT
import com.example.novalinea.common.Constants.TYPE_MESSAGE_TEXT
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.MessageSend
import com.example.novalinea.domain.model.ProjectOpen
import com.example.novalinea.domain.model.ProjectTape
import com.example.novalinea.domain.model.Response
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
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _stateProject = MutableLiveData<Response<ProjectOpen?>>()
    val stateProject: LiveData<Response<ProjectOpen?>> get() = _stateProject

    private val _stateSend = MutableLiveData<Response<Boolean>>()
    val stateSend: LiveData<Response<Boolean>> get() = _stateSend

    init {
        savedStateHandle.get<Parcelable>(ARGUMENT_PROJECT_DATA)?.let { project ->
            Log.d(TAG, project.toString())
        }
        savedStateHandle.get<ProjectTape>(ARGUMENT_PROJECT_DATA)?.let { project ->
            Log.d(TAG, project.toString())
        }
        /*savedStateHandle.get<String>(ARGUMENT_PROJECT_ID_KEY)?.let { projectID ->
            getProjectById(projectID)
            incrementView(projectID)
        }*/
    }

    fun getProjectById(projectID: String) {
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

    fun sendMessage() {
        savedStateHandle.get<String>(ARGUMENT_USER_ID_KEY)?.let { toID ->
            viewModelScope.launch {
                USER.id?.let { fromID ->
                    if (toID != fromID) {
                        val messageSend = MessageSend(
                            text = BUY_PROJECT_MESSAGE,
                            from = fromID,
                            to = toID,
                            type = TYPE_MESSAGE_TEXT
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