package com.example.projectunion.domain.model

import java.util.*

data class ProjectOpen (
	val id: String? = null,
	val title: String? = null,
	val description: String? = null,
	val type: String? = null,
	val price: Int? = null,
	//val paymentSystem: String,
	//val progress: Int,
	//val listStaff: List<String>,
	val createdAt: Date? = null,
	val updatedAt: Date? = null,
	val likes: Int? = null,
	val views: Int? = null,
	val creatorID: String? = null
)