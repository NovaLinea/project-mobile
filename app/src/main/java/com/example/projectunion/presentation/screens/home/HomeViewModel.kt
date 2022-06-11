package com.example.projectunion.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectunion.core.Resource
import com.example.projectunion.domain.use_case.UseCases
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
	private val useCases: UseCases
): ViewModel() {
	/*private val _projectsState = mutableStateOf(Resource.Loading)
	val projectsState: State<HomeState> = _projectsState

	init {
		getProjects()
	}

	private fun getProjects() {

	}*/
}