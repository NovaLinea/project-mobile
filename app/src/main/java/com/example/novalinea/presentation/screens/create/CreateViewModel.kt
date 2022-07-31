package com.example.novalinea.presentation.screens.create

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.ProjectCreate
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.TypesProject
import com.example.novalinea.domain.use_case.CreateProjectUseCase
import com.example.novalinea.presentation.screens.create.components.create_text_field.CreateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val createProjectUseCase: CreateProjectUseCase
): ViewModel() {

    val type by lazy { mutableStateOf(TypesProject.SALE) }
    val title by lazy { CreateState() }
    val description by lazy { CreateState() }
    val price by lazy { CreateState() }
    var images = mutableStateListOf<Uri>()

    private val _stateCreate = MutableLiveData<Response<Boolean>>()
    val stateCreate: LiveData<Response<Boolean>> get() = _stateCreate

    fun addImageProject(uri: Uri) {
        images.add(uri)
    }

    fun deleteImageProject(index: Int) {
        images.removeAt(index)
    }

    fun createProject() {
        viewModelScope.launch {
            USER.id?.let {
                val project = ProjectCreate(
                    title = title.text,
                    description = description.text,
                    type = type.value,
                    price = price.text.replace(" ", "").toInt(),
                    creatorID = it
                )
                createProjectUseCase(project, images).collect { response ->
                    _stateCreate.postValue(response)
                }
            }
        }
    }
}