package com.example.novalinea.domain.model

data class ProjectCreate (
	val title: String,
	val description: String,
	val type: TypesProject,
	val price: Int,
	val creatorID: String
)