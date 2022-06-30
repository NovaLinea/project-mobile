package com.example.projectunion.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectunion.domain.model.ProjectTape
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.use_case.CheckAuthorizedUseCase
import com.example.projectunion.domain.use_case.GetProjectsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val checkAuthorizedUseCase: CheckAuthorizedUseCase,
	private val getProjectsUseCase: GetProjectsUseCase
): ViewModel() {

	val isAuth get() = checkAuthorizedUseCase()

	private val _state = MutableLiveData<Response<List<ProjectTape>>>()
	val state: LiveData<Response<List<ProjectTape>>> get() = _state

	init {
		getProjects()
	}

	private fun getProjects() {
		viewModelScope.launch {
			getProjectsUseCase().collect { response ->
				_state.postValue(response)
			}
		}
	}
}