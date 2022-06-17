package com.example.projectunion.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectunion.domain.repository.AuthRepository
import com.example.projectunion.domain.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val authRepository: AuthRepository,
	private val projectRepository: ProjectRepository
): ViewModel() {
	val isAuth get() = authRepository.authorized()

	private val _state = MutableLiveData<HomeState>()
	val state: LiveData<HomeState> get() = _state

	init {
		getProjects()
	}

	private fun getProjects() {

	}
}