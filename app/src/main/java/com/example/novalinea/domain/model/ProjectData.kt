package com.example.novalinea.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProjectData (
	var id: String? = null,
	val title: String? = null,
	val description: String? = null,
	val images: List<String>? = null,
	val type: String? = null,
	val price: Int? = null,
	//val paymentSystem: String,
	//val progress: Int,
	//val listStaff: List<String>,
	val createdAt: String = "",
	val updatedAt: String = "",
	val views: Int? = null,
	val creatorID: String? = null,
	var creatorName: String? = null,
	var creatorPhoto: String? = null
): Parcelable {

	companion object Keys {
		const val PROJECT = "project"
	}
}