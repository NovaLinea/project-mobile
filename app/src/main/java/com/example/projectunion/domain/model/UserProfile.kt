package com.example.projectunion.domain.model

import android.text.format.Time

data class UserProfile (
	val id: String,
	val name: String,
	val email: String,
	val description: String,
	val createdAt: Time,
)