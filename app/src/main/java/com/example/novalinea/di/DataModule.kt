package com.example.novalinea.di

import android.content.Context
import com.example.novalinea.data.authentication.Authentication
import com.example.novalinea.data.authentication.AuthenticationImpl
import com.example.novalinea.data.firestoreDB.FirestoreDB
import com.example.novalinea.data.firestoreDB.FirestoreDBImpl
import com.example.novalinea.data.realtimeDB.RealtimeDB
import com.example.novalinea.data.realtimeDB.RealtimeDBImpl
import com.example.novalinea.data.repository.*
import com.example.novalinea.data.storage.Storage
import com.example.novalinea.data.storage.StorageImpl
import com.example.novalinea.domain.repository.AuthRepository
import com.example.novalinea.domain.repository.MessageRepository
import com.example.novalinea.domain.repository.ProjectRepository
import com.example.novalinea.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule() {

	@Provides
	@Singleton
	fun provideFirebaseAuth() = Firebase.auth

	@Provides
	@Singleton
	fun provideFirestore() = Firebase.firestore

	@Provides
	@Singleton
	fun provideRealtime() = Firebase.database.reference

	@Provides
	@Singleton
	fun provideStorageReference() = FirebaseStorage.getInstance().reference


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
	fun provideRealtimeDB(
		db: DatabaseReference
	): RealtimeDB {
		return RealtimeDBImpl(db)
	}

	@Provides
	@Singleton
	fun provideStorage(
		storageRef: StorageReference,
		@ApplicationContext context: Context
	): Storage {
		return StorageImpl(storageRef, context)
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
	fun provideUserRepository(
		firestoreDB: FirestoreDB,
		storageDB: Storage,
	): UserRepository {
		return UserRepositoryImpl(firestoreDB, storageDB)
	}

	@Provides
	@Singleton
	fun provideProjectRepository(
		firestoreDB: FirestoreDB,
		storageDB: Storage
	): ProjectRepository {
		return ProjectRepositoryImpl(firestoreDB, storageDB)
	}

	@Provides
	@Singleton
	fun provideMessageRepository(
		realtimeDB: RealtimeDB,
		firestoreDB: FirestoreDB,
		storageDB: Storage
	): MessageRepository {
		return MessageRepositoryImpl(realtimeDB, firestoreDB, storageDB)
	}
}