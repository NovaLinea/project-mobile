package com.example.projectunion.di

import com.example.projectunion.data.repository.AuthRepositoryImpl
import com.example.projectunion.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

	@Provides
	@Singleton
	fun provideFirebaseAuth() = Firebase.auth

	@Provides
	@Singleton
	fun provideAuthRepository(auth: FirebaseAuth): AuthRepository {
		return AuthRepositoryImpl(auth)
	}
}