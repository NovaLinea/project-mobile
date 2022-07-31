package com.example.novalinea.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photos (
	val photos: List<String?>
): Parcelable