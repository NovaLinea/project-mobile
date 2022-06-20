package com.example.projectunion.presentation.screens.create

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectunion.domain.model.ProjectCreate
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.use_case.CreateProjectUseCase
import com.example.projectunion.domain.use_case.GetUserUseCase
import com.example.projectunion.presentation.screens.create.components.create_text_field.CreateTextState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val createProjectUseCase: CreateProjectUseCase
): ViewModel() {
    val title by lazy { CreateTextState() }
    val description by lazy { CreateTextState() }
    val price by lazy { CreateTextState() }
    var images = mutableListOf<Uri>()

    private val _state = MutableLiveData<Response<Boolean>>()
    val state: LiveData<Response<Boolean>> get() = _state

    fun createProject(typeProject: String) {
        var creatorID = mutableStateOf("")
        viewModelScope.launch {
            getUserUseCase().collect { response ->
                if (response is Response.Success) {
                    if (response.data != null) {
                        creatorID.value = response.data.uid
                    }
                }
            }
        }

        viewModelScope.launch {
            val dateNow = Date()

            val project = ProjectCreate(
                title = title.text,
                description = description.text,
                type = typeProject,
                price = price.text.toInt(),
                createdAt = dateNow,
                updatedAt = dateNow,
                likes = 0,
                views = 0,
                creatorID = creatorID.value
            )
            createProjectUseCase(project, images).collect { response ->
                _state.postValue(response)
            }
        }
    }
}