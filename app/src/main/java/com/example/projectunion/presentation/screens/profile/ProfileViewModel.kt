package com.example.projectunion.presentation.screens.profile

import androidx.lifecycle.ViewModel
import com.example.projectunion.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
	private val authRepository: AuthRepository
): ViewModel() {

	fun logout() {
		authRepository.logout()
	}
}