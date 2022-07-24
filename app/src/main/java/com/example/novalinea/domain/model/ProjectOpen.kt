package com.example.novalinea.domain.model

data class ProjectOpen (
	val title: String? = null,
	val description: String? = null,
	val images: List<String>? = null,
	val type: String? = null,
	//val paymentSystem: String,
	//val progress: Int,
	//val listStaff: List<String>,
	val createdAt: Any = "",
	val updatedAt: Any = "",
	val views: Int? = null,
	val creatorID: String? = null,
	var creatorName: String? = null,
	var creatorPhoto: String? = null
)