package com.example.projectunion.di

import com.example.projectunion.data.authentication.Authentication
import com.example.projectunion.data.authentication.AuthenticationImpl
import com.example.projectunion.data.firestoreDB.FirestoreDB
import com.example.projectunion.data.firestoreDB.FirestoreDBImpl
import com.example.projectunion.data.repository.AuthRepositoryImpl
import com.example.projectunion.data.repository.ProjectRepositoryImpl
import com.example.projectunion.data.repository.UserRepositoryImpl
import com.example.projectunion.domain.repository.AuthRepository
import com.example.projectunion.domain.repository.ProjectRepository
import com.example.projectunion.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
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
	fun provideFirestore() = Firebase.firestore

	@Provides
	@Singleton
	fun provideStorage() = FirebaseStorage.getInstance()

	@Provides
	@Singleton
	fun provideAuthentication(
		auth: FirebaseAuth
	): Authentication {
		return AuthenticationImpl(auth)
	}

	@Provides
	@Singleton
	fun provideFirestoreDB(
		db: FirebaseFirestore
	): FirestoreDB {
		return FirestoreDBImpl(db)
	}

	@Provides
	@Singleton
	fun provideAuthRepository(
		authentication: Authentication,
		firestoreDB: FirestoreDB
	): AuthRepository {
		return AuthRepositoryImpl(authentication, firestoreDB)
	}

	@Provides
	@Singleton
	fun provideUserRepository(): UserRepository {
		return UserRepositoryImpl()
	}

	@Provides
	@Singleton
	fun provideProjectRepository(
		firestoreDB: FirestoreDB
	): ProjectRepository {
		return ProjectRepositoryImpl(firestoreDB)
	}
}