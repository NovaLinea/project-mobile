package com.example.projectunion.domain.model

data class ProjectCreate (
	val title: String,
	val description: String,
	val type: String,
	val price: Int,
	val creatorID: String
)