package com.example.projectunion.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.example.projectunion.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val authRepository: AuthRepository
): ViewModel() {

	val isAuth get() = authRepository.authorized()
}