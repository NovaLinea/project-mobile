package com.example.projectunion.presentation.screens.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectunion.domain.model.Project
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.use_case.CreateProjectUseCase
import com.example.projectunion.presentation.screens.create.components.create_text_field.CreateTextState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val createProjectUseCase: CreateProjectUseCase
): ViewModel() {
    val title by lazy { CreateTextState() }
    val description by lazy { CreateTextState() }
    val price by lazy { CreateTextState() }

    private val _state = MutableLiveData<Response<Boolean>>()
    val state: LiveData<Response<Boolean>> get() = _state

    fun createProject(typeProject: String) {
        viewModelScope.launch {
            val dateNow = Date()
            val project = Project(
                uid = null,
                title = title.text,
                description = description.text,
                type = typeProject,
                price = price.text.toInt(),
                createdAt = dateNow,
                updatedAt = dateNow,
                likes = 0,
                views = 0,
                creatorName = "Vanya Palamarenko"
            )
            createProjectUseCase(project).collect { response ->
                _state.postValue(response)
            }
        }
    }
}