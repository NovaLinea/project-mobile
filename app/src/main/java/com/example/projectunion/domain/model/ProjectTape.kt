package com.example.projectunion.domain.model

data class ProjectTape (
	var id: String? = null,
	val title: String? = null,
	val description: String? = null,
	val images: List<String>? = null,
	val type: String? = null,
	val price: Int? = null,
	//val progress: Int,
	//val listStaff: List<String>,
	val createdAt: Any = "",
	val views: Int? = null,
	val creatorID: String? = null,
	var creatorName: String? = null,
	var creatorPhoto: String? = null
)