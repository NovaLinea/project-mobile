package com.example.projectunion.presentation.screens.create

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.projectunion.common.Constants.ARGUMENT_PROJECT_TYPE_KEY
import com.example.projectunion.domain.model.ProjectCreate
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.use_case.CreateProjectUseCase
import com.example.projectunion.domain.use_case.GetAuthCurrentUserUseCase
import com.example.projectunion.presentation.screens.create.components.create_text_field.CreateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val getAuthCurrentUserUseCase: GetAuthCurrentUserUseCase,
    private val createProjectUseCase: CreateProjectUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val title by lazy { CreateState() }
    val description by lazy { CreateState() }
    val price by lazy { CreateState() }
    var images = mutableListOf<Uri>()

    private val _state = MutableLiveData<Response<Boolean>>()
    val state: LiveData<Response<Boolean>> get() = _state

    fun createProject() {
        savedStateHandle.get<String>(ARGUMENT_PROJECT_TYPE_KEY)?.let { typeProject ->
            var creatorID = mutableStateOf("")
            viewModelScope.launch {
                creatorID.value = getAuthCurrentUserUseCase()?.uid.toString()
            }

            viewModelScope.launch {
                val project = ProjectCreate(
                    title = title.text,
                    description = description.text,
                    type = typeProject,
                    price = price.text.toInt(),
                    creatorID = creatorID.value
                )
                createProjectUseCase(project, images).collect { response ->
                    _state.postValue(response)
                }
            }
        }
    }
}