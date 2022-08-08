package com.example.novalinea.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novalinea.domain.use_case.CheckAuthorizedUseCase
import com.example.novalinea.domain.use_case.GetProjectsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val checkAuthorizedUseCase: CheckAuthorizedUseCase,
	getProjectsUseCase: GetProjectsUseCase
): ViewModel() {

	val isAuth get() = checkAuthorizedUseCase()
	val projects = getProjectsUseCase(viewModelScope)
}