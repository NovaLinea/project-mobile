package com.example.novalinea.domain.model

import com.google.firebase.Timestamp

data class UserProfile (
	var id: String? = null,
	var name: String? = null,
	var email: String? = null,
	var description: String = "",
	var photo: String? = null,
	var verify: Boolean = false,
	var count_projects: Int = 0,
	var follows: List<String> = emptyList(),
	var followings: List<String> = emptyList(),
	var createdAt: Timestamp? = null
)