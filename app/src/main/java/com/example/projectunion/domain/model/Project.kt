package com.example.projectunion.domain.model

import android.net.Uri
import java.util.*


data class Project (
	val uid: String?,
	val title: String,
	val description: String,
	val images: List<Uri>,
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