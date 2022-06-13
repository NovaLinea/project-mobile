package com.example.projectunion.di

import com.example.projectunion.domain.use_case.LoginByEmailUseCase
import org.koin.dsl.module

val domainModule = module {

	factory<LoginByEmailUseCase> {
		LoginByEmailUseCase(repository = get())
	}
}