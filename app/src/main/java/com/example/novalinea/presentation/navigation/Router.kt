package com.example.novalinea.presentation.navigation

import android.os.Parcelable

interface Router {
	fun routeTo(route: String, param: Pair<String, Parcelable>? = null) {
		throw NotImplementedError(message = "You used router, but don't implemented it for screen $route with param $param")
	}
}

fun createExternalRouter(block: (String, Pair<String, Parcelable>?) -> Unit): Router = object : Router {
	override fun routeTo(route: String, param: Pair<String, Parcelable>?) {
		block.invoke(route, param)
	}
}