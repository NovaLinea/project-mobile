package com.example.novalinea.presentation.navigation

import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavController.navigate(route: String, param: Pair<String, Parcelable>?, builder: NavOptionsBuilder.() -> Unit = {}) {
	param?.let { data ->
		this.currentBackStackEntry?.savedStateHandle?.set(
			data.first,
			data.second
		)
	}
	navigate(route, builder)
}

fun NavController.navigate(route: String, param: Parcelable?, key: String, builder: NavOptionsBuilder.() -> Unit = {}) {
	this.currentBackStackEntry?.savedStateHandle?.set(
		key,
		param
	)
	navigate(route, builder)
}