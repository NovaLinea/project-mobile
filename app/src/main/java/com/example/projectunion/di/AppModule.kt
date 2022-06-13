package com.example.projectunion.di

import com.example.projectunion.presentation.screens.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

	viewModel<LoginViewModel>{
		LoginViewModel(
			loginByEmailUseCase = get()
		)
	}
}