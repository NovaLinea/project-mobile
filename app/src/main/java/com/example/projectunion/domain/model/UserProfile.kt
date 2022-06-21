package com.example.projectunion.domain.model

data class UserProfile (
	val id: String,
	var name: String = "",
	var email: String = "",
	var description: String = "",
	var photo: String = ""
)