package com.example.projectunion.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectunion.domain.model.ProjectTape
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.use_case.CheckAuthorizedUseCase
import com.example.projectunion.domain.use_case.GetProjectsUseCase
import com.example.projectunion.presentation.components.search_field.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val checkAuthorizedUseCase: CheckAuthorizedUseCase,
	private val getProjectsUseCase: GetProjectsUseCase
): ViewModel() {

	val isAuth get() = checkAuthorizedUseCase()

	private val _stateGet = MutableLiveData<Response<List<ProjectTape>>>()
	val stateGet: LiveData<Response<List<ProjectTape>>> get() = _stateGet

	private val _stateUpdate = MutableLiveData<Response<List<ProjectTape>>>()
	val stateUpdate: LiveData<Response<List<ProjectTape>>> get() = _stateUpdate

	val search by lazy { SearchState() }

	init {
		getProjects()
	}

	fun getProjects() {
		viewModelScope.launch {
			getProjectsUseCase().collect { response ->
				_stateGet.postValue(response)
			}
		}
	}

	fun updateProjects() {
		viewModelScope.launch {
			getProjectsUseCase().collect { response ->
				_stateUpdate.postValue(response)
			}
		}
	}
}