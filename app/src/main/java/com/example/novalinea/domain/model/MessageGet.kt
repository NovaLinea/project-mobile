package com.example.novalinea.domain.model

data class MessageGet(
	var id: String = "",
	val text: String = "",
	val from: String = "",
	val type: String = "",
	val timestamp: Any = ""
)