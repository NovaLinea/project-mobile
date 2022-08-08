package com.example.novalinea.presentation.screens.create

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
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
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val createProjectUseCase: CreateProjectUseCase,
    @ApplicationContext private val context: Context
): ViewModel() {
    val type by lazy { mutableStateOf(TypesProject.SALE) }
    val title by lazy { CreateState() }
    val description by lazy { CreateState() }
    val price by lazy { CreateState() }
    val listStaff by lazy { mutableStateListOf<String>() }
    val images by lazy { mutableStateListOf<Uri>() }
    private val compressImages by lazy { mutableListOf<ByteArray>() }

    private val _stateCreate = MutableLiveData<Response<Boolean>>()
    val stateCreate: LiveData<Response<Boolean>> get() = _stateCreate

    fun onAddImage(uri: Uri) {
        images.add(uri)
        viewModelScope.launch {
            val compress = compressImage(uri)
            compressImages.add(compress)
        }
    }

    private fun compressImage(photoUri: Uri): ByteArray {
        val photoBitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, photoUri)
        val stream = ByteArrayOutputStream()
        photoBitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
        return stream.toByteArray()
    }

    fun onDeleteImage(index: Int) {
        images.removeAt(index)
        compressImages.removeAt(index)
    }

    fun createProject() {
        viewModelScope.launch {
            USER.id?.let {
                val project = ProjectCreate(
                    title = title.text,
                    description = description.text,
                    type = type.value,
                    creatorID = it
                )

                when (type.value) {
                    TypesProject.SALE ->
                        project.price = price.text.replace(" ", "").toInt()

                    TypesProject.TEAM ->
                        project.staff = listStaff
                }

                createProjectUseCase(project, compressImages).collect { response ->
                    _stateCreate.postValue(response)
                }
            }
        }
    }
}