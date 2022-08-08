package com.example.novalinea.di

import com.example.novalinea.data.pagging.ProjectsPagingSource
import com.example.novalinea.domain.repository.AuthRepository
import com.example.novalinea.domain.repository.MessageRepository
import com.example.novalinea.domain.repository.ProjectRepository
import com.example.novalinea.domain.repository.UserRepository
import com.example.novalinea.domain.use_case.*
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

	// Paging
	@Provides
	fun provideProjectsPagingSource(
		db: FirebaseFirestore
	) = ProjectsPagingSource(db)


	// Auth
	@Provides
	fun provideCheckAuthorizedUseCase(authRepository: AuthRepository): CheckAuthorizedUseCase {
		return CheckAuthorizedUseCase(repository = authRepository)
	}

	@Provides
	fun provideCheckVerifyEmailUseCase(authRepository: AuthRepository): CheckVerifyEmailUseCase {
		return CheckVerifyEmailUseCase(repository = authRepository)
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
	fun provideGetProjectsUseCase(
		source: ProjectsPagingSource
	): GetProjectsUseCase {
		return GetProjectsUseCase(source)
	}

	@Provides
	fun provideGetProjectsUserUseCase(
		db: FirebaseFirestore
	): GetProjectsUserUseCase {
		return GetProjectsUserUseCase(db)
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

	@Provides
	fun provideUploadPhotoUserUseCase(userRepository: UserRepository): UploadPhotoUserUseCase {
		return UploadPhotoUserUseCase(repository = userRepository)
	}

	@Provides
	fun provideDeletePhotoUserUseCase(userRepository: UserRepository): DeletePhotoUserUseCase {
		return DeletePhotoUserUseCase(repository = userRepository)
	}

	@Provides
	fun provideEditProfileUseCase(userRepository: UserRepository): EditProfileUseCase {
		return EditProfileUseCase(repository = userRepository)
	}

	// Message
	@Provides
	fun provideGetChatsUseCase(messageRepository: MessageRepository): GetChatsUseCase {
		return GetChatsUseCase(repository = messageRepository)
	}

	@Provides
	fun provideGetMessagesUseCase(messageRepository: MessageRepository): GetMessagesUseCase {
		return GetMessagesUseCase(repository = messageRepository)
	}

	@Provides
	fun provideSendMessageUseCase(messageRepository: MessageRepository): SendMessageUseCase {
		return SendMessageUseCase(repository = messageRepository)
	}
}