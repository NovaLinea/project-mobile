package com.example.projectunion.di

import com.example.projectunion.data.repository.AuthRepositoryImpl
import com.example.projectunion.domain.repository.AuthRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val dataModule = module {

	single<AuthRepository> {
		AuthRepositoryImpl(Firebase.auth)
	}
}