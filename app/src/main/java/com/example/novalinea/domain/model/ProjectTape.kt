package com.example.novalinea.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProjectTape (
	var id: String? = null,
	val title: String? = null,
	val description: String? = null,
	val images: List<String>? = null,
	val type: TypesProject? = null,
	val price: Int? = null,
	val staff: List<String>? = null,
	val creatorID: String? = null,
	var creatorName: String? = null,
	var creatorPhoto: String? = null
): Parcelable