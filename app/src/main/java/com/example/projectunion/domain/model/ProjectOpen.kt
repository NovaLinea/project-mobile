package com.example.projectunion.domain.model

import java.util.*

data class ProjectOpen (
	val uid: String,
	val title: String,
	val description: String,
	val type: String,
	val price: Int,
	//val paymentSystem: String,
	//val progress: Int,
	//val listStaff: List<String>,
	val createdAt: Date,
	val updatedAt: Date,
	val likes: Int,
	val views: Int,
	val creatorID: String
)