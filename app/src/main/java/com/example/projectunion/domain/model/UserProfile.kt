package com.example.projectunion.domain.model

data class UserProfile (
	val id: String? = null,
	var name: String? = null,
	var email: String? = null,
	var description: String? = null,
	var photo: String? = null
)