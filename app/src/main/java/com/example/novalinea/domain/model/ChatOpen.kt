package com.example.novalinea.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChatOpen(
	val userId: String? = "",
	var userName: String? = "",
	var userPhoto: String? = null,
	var userVerify: Boolean = false
): Parcelable