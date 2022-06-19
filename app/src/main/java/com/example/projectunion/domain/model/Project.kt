package com.example.projectunion.domain.model


data class Project (
	val id: String?,
	val title: String,
	val description: String,
	//val type: String,
	val price: Int,
	//val paymentSystem: String,
	//val listStaff: List<String>,
	val createdAt: String,
	//val updatedAt: Time,
	//val likes: Int,
	//val views: Int,
	//val creatorID: Int,
	val creatorName: String,
	//val creatorPhoto: String
)