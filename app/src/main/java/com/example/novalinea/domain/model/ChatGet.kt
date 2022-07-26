package com.example.novalinea.domain.model

data class ChatGet(
	val userId: String = "",
	var userName: String = "",
	var userPhoto: String? = null,
	val lastMessage: String = "",
	val timestamp: Any = "",
	val viewed: Boolean = false,
	val countNewMessages: Int = 0
)