package com.example.projectunion.domain.model

data class MessageSend(
	val message: String = "",
	val from: String = "",
	var timestamp: Map<String, String> = emptyMap(),
)