package com.example.projectunion.domain.model

data class ProjectCreate (
	val title: String,
	val description: String,
	val type: String,
	val price: Int,
	//val paymentSystem: String,
	//val progress: Int,
	//val listStaff: List<String>,
	val creatorID: String
)