package com.example.novalinea.domain.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProjectOpen (
	val views: Int? = null,
	val createdAt: Timestamp? = null,
	val updatedAt: Timestamp? = null
): Parcelable