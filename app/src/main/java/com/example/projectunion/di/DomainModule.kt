package com.example.projectunion.di

import com.example.projectunion.domain.repository.AuthRepository
import com.example.projectunion.domain.repository.ProjectRepository
import com.example.projectunion.domain.use_case.*
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
	fun provideCheckAuthorizedUseCase(authRepository: AuthRepository): CheckAuthorizedUseCase {
		return CheckAuthorizedUseCase(repository = authRepository)
	}

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

	@Provides
	fun provideGetUserUseCase(authRepository: AuthRepository): GetUserUseCase {
		return GetUserUseCase(repository = authRepository)
	}

	@Provides
	fun provideCreateProjectUseCase(projectRepository: ProjectRepository): CreateProjectUseCase {
		return CreateProjectUseCase(repository = projectRepository)
	}
}