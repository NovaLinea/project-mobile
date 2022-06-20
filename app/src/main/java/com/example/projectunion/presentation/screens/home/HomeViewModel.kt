package com.example.projectunion.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectunion.domain.repository.AuthRepository
import com.example.projectunion.domain.repository.ProjectRepository
import com.example.projectunion.domain.use_case.CheckAuthorizedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val checkAuthorizedUseCase: CheckAuthorizedUseCase
): ViewModel() {
	val isAuth get() = checkAuthorizedUseCase()

	private val _state = MutableLiveData<HomeState>()
	val state: LiveData<HomeState> get() = _state

	init {
		getProjects()
	}

	private fun getProjects() {

	}
}