package com.example.projectunion.domain.model

data class MessageSend(
	val text: String = "",
	val from: String = "",
	val to: String = "",
	val type: String = ""
)