package com.example.projectunion.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.example.projectunion.domain.repository.AuthRepository
import com.example.projectunion.domain.use_case.CheckAuthorizedUseCase
import com.example.projectunion.domain.use_case.GetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val checkAuthorizedUseCase: CheckAuthorizedUseCase,
	private val getUserByIdUseCase: GetUserByIdUseCase
): ViewModel() {

	val isAuth get() = checkAuthorizedUseCase()

	init {
	    initializeUser()
	}

	fun initializeUser() {
		if (isAuth) {
			// getUserByIdUseCase
		}
	}
}