package com.example.novalinea.presentation.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavController.navigate(route: String, param: Pair<String, Parcelable>?, builder: NavOptionsBuilder.() -> Unit = {}) {
	param?.let { this.currentBackStackEntry?.arguments?.putParcelable(param.first, param.second) }
	navigate(route, builder)
}

fun NavController.navigate(route: String, params: List<Pair<String, Parcelable>>?, builder: NavOptionsBuilder.() -> Unit = {}) {
	params?.let {
		val arguments = this.currentBackStackEntry?.arguments
		params.forEach { arguments?.putParcelable(it.first, it.second) }
	}
	navigate(route, builder)
}

fun NavController.navigate(route: String, param: Bundle?, builder: NavOptionsBuilder.() -> Unit = {}) {
	this.currentBackStackEntry?.arguments?.putAll(param)
	navigate(route, builder)
}

fun NavController.navigate(route: String, param: Parcelable?, key: String, builder: NavOptionsBuilder.() -> Unit = {}) {
	this.currentBackStackEntry?.savedStateHandle?.set(
		key,
		param
	)
	navigate(route, builder)
}