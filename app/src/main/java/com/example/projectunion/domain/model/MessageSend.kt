package com.example.projectunion.domain.model

data class MessageSend(
	val message: String = "",
	val from: String = "",
	val to: String = "",
	val type: String = ""
)