package com.example.projectunion.di

import com.example.projectunion.domain.repository.AuthRepository
import com.example.projectunion.domain.repository.ProjectRepository
import com.example.projectunion.domain.repository.UserRepository
import com.example.projectunion.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

	// Auth
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
	fun provideGetAuthCurrentUserUseCase(authRepository: AuthRepository): GetAuthCurrentUserUseCase {
		return GetAuthCurrentUserUseCase(repository = authRepository)
	}

	// Project
	@Provides
	fun provideCreateProjectUseCase(projectRepository: ProjectRepository): CreateProjectUseCase {
		return CreateProjectUseCase(repository = projectRepository)
	}

	@Provides
	fun provideGetProjectsUseCase(projectRepository: ProjectRepository): GetProjectsUseCase {
		return GetProjectsUseCase(repository = projectRepository)
	}

	@Provides
	fun provideGetProjectsUserUseCase(projectRepository: ProjectRepository): GetProjectsUserUseCase {
		return GetProjectsUserUseCase(repository = projectRepository)
	}

	@Provides
	fun provideGetProjectByIdUseCase(projectRepository: ProjectRepository): GetProjectByIdUseCase {
		return GetProjectByIdUseCase(repository = projectRepository)
	}

	@Provides
	fun provideIncrementViewUseCase(projectRepository: ProjectRepository): IncrementViewUseCase {
		return IncrementViewUseCase(repository = projectRepository)
	}

	// User
	@Provides
	fun provideGetUserByIdUseCase(userRepository: UserRepository): GetUserByIdUseCase {
		return GetUserByIdUseCase(repository = userRepository)
	}
}