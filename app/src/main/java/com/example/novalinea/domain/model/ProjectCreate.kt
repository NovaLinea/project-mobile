package com.example.novalinea.domain.model

data class ProjectCreate (
	val title: String,
	val description: String,
	val type: TypesProject,
	var price: Int = 0,
	var staff: List<String> = emptyList(),
	val creatorID: String
)