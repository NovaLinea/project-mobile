package com.example.projectunion.domain.model

data class MessageGet(
	var id: String = "",
	val text: String = "",
	val from: String = "",
	val type: String = "",
	val timestamp: Any = ""
)