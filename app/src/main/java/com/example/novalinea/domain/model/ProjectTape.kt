package com.example.novalinea.domain.model

import android.os.Parcelable
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_DATA
import com.google.firebase.Timestamp
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProjectTape (
	var id: String? = null,
	val title: String? = null,
	val description: String? = null,
	val images: List<String>? = null,
	val type: String? = null,
	val price: Int? = null,
	//val progress: Int,
	//val listStaff: List<String>,
	val views: Int? = null,
	val creatorID: String? = null,
	var creatorName: String? = null,
	var creatorPhoto: String? = null,
	val createdAt: Timestamp? = null,
	val updatedAt: Timestamp? = null
): Parcelable