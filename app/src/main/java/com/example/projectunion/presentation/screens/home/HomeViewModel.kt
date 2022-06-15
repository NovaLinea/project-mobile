package com.example.projectunion.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.repository.AuthRepository
import com.example.projectunion.domain.repository.ProjectRepository
import com.example.projectunion.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val authRepository: AuthRepository,
	private val projectRepository: ProjectRepository
): ViewModel() {
	val isAuth get() = authRepository.authorized()

	private val _state = mutableStateOf(HomeState())
	val state: State<HomeState> get() = _state

	init {
		getProjects()
	}

	private fun getProjects() {

	}
}