package com.example.projectunion.data.repository

import com.example.projectunion.common.Constants.ERROR_VERIFY_EMAIL
import com.example.projectunion.common.Constants.INVALID_REGISTER
import com.example.projectunion.data.authentication.Authentication
import com.example.projectunion.data.firestoreDB.FirestoreDB
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.model.UserRegister
import com.example.projectunion.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
	private val authentication: Authentication,
	private val firestoreDB: FirestoreDB
) : AuthRepository {

	override fun authorized() = authentication.authorized()

	override fun verified() = authentication.verified()

	override fun loginByEmail(userData: UserLogin) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)

			authentication.loginByEmail(userData).collect { response ->
				when(response) {
					is Response.Loading -> emit(response)
					is Response.Error -> emit(response)
					is Response.Success -> {
						if (authentication.verified() == true)
							emit(Response.Success(true))
						else
							emit(Response.Error(ERROR_VERIFY_EMAIL))
					}
				}
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun registerByEmail(userData: UserRegister) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)

			authentication.registerByEmail(userData).collect { responseAuth ->
				when(responseAuth) {
					is Response.Loading -> emit(responseAuth)
					is Response.Error -> emit(responseAuth)
					is Response.Success -> {
						authentication.verifyEmail().collect { responseVerify ->
							when(responseVerify) {
								is Response.Loading -> emit(responseVerify)
								is Response.Error -> emit(responseVerify)
								is Response.Success -> {
									responseAuth?.let { user ->
										if (user.data != null)
											firestoreDB.createUser(userData, user.data.uid).collect { emit(it) }
										else
											emit(Response.Error(INVALID_REGISTER))
									}
								}
							}
						}
					}
				}
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun logout() = authentication.logout()

	override fun getAuthCurrentUser() = authentication.getAuthCurrentUser()
}