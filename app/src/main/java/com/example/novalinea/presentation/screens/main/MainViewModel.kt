package com.example.novalinea.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.example.novalinea.domain.use_case.CheckAuthorizedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val checkAuthorizedUseCase: CheckAuthorizedUseCase
): ViewModel() {

	val isAuth get() = checkAuthorizedUseCase()
}