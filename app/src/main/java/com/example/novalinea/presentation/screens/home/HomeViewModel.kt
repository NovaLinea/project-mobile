package com.example.novalinea.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novalinea.domain.model.ProjectTape
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.use_case.CheckAuthorizedUseCase
import com.example.novalinea.domain.use_case.GetProjectsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val checkAuthorizedUseCase: CheckAuthorizedUseCase,
	private val getProjectsUseCase: GetProjectsUseCase
): ViewModel() {

	val isAuth get() = checkAuthorizedUseCase()

	private val _stateGet by lazy { MutableLiveData<Response<List<ProjectTape>>>() }
	val stateGet: LiveData<Response<List<ProjectTape>>> get() = _stateGet

	//private val _stateUpdate = MutableLiveData<Response<List<ProjectTape>>>()
	//val stateUpdate: LiveData<Response<List<ProjectTape>>> get() = _stateUpdate

	//private val _stateLoad = MutableLiveData<Response<List<ProjectTape>>>()
	//val stateLoad: LiveData<Response<List<ProjectTape>>> get() = _stateLoad

	//var page = 1

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

	/*fun loadProjects() {
		viewModelScope.launch {
			getProjectsUseCase().collect { response ->
				_stateLoad.postValue(response)
			}
		}
	}*/

	/*fun updateProjects() {
		viewModelScope.launch {
			getProjectsUseCase().collect { response ->
				_stateUpdate.postValue(response)
			}
		}
	}*/
}