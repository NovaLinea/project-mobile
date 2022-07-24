package com.example.novalinea.presentation.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf

//interface Router {
//	fun routeTo(route: String, params: Bundle = bundleOf()) {
//		throw NotImplementedError(message = "You used router, but don't implemented it for screen $route with params $params")
//	}
//}
//
//fun createExternalRouter(block: (String, Bundle) -> Unit): Router = object : Router {
//	override fun routeTo(route: String, params: Bundle) {
//		block.invoke(route, params)
//	}
//}

interface Router {
	fun routeTo(route: String, param: Parcelable? = null, key: String = "") {
		throw NotImplementedError(message = "You used router, but don't implemented it for screen $route with param $param")
	}
}

fun createExternalRouter(block: (String, Parcelable?, String) -> Unit): Router = object : Router {
	override fun routeTo(route: String, param: Parcelable?, key: String) {
		block.invoke(route, param, key)
	}
}

//interface Router {
//	fun routeTo(route: String)
//}
//
//fun createExternalRouter(
//	block: (String) -> Unit
//): Router = object : Router {
//	override fun routeTo(route: String) {
//		block.invoke(route)
//	}
//}