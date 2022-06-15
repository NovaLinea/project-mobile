package com.example.projectunion.di

import com.example.projectunion.domain.repository.AuthRepository
import com.example.projectunion.domain.use_case.LoginByEmailUseCase
import com.example.projectunion.domain.use_case.LogoutUseCase
import com.example.projectunion.domain.use_case.RegisterByEmailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

	@Provides
	fun provideLoginByEmailUseCase(authRepository: AuthRepository): LoginByEmailUseCase {
		return LoginByEmailUseCase(repository = authRepository)
	}

	@Provides
	fun provideRegisterByEmailUseCase(authRepository: AuthRepository): RegisterByEmailUseCase {
		return RegisterByEmailUseCase(repository = authRepository)
	}

	@Provides
	fun provideLogoutUseCase(authRepository: AuthRepository): LogoutUseCase {
		return LogoutUseCase(repository = authRepository)
	}
}