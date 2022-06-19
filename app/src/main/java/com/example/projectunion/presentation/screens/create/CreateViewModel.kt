package com.example.projectunion.presentation.screens.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectunion.common.Constants
import com.example.projectunion.domain.model.Project
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.use_case.CreateProjectUseCase
import com.example.projectunion.presentation.components.name_field.NameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val createProjectUseCase: CreateProjectUseCase
): ViewModel() {
    val title by lazy { NameState() }
    val description by lazy { NameState() }
    val price by lazy { MutableLiveData<Int>() }

    private val _state = MutableLiveData<Response<Boolean>>()
    val state: LiveData<Response<Boolean>> get() = _state

    fun createProject() {
        viewModelScope.launch {
            val currentDate = Constants.TIME_FORMAT.format(Date())
            val project = Project(
                id = null,
                title = title.text,
                description = description.text,
                price = 15000,
                createdAt = currentDate,
                creatorName = "Vanya Palamarenko"
            )
            createProjectUseCase(project).collect { response ->
                _state.postValue(response)
            }
        }
    }
}