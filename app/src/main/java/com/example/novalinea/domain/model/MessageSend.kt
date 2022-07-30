package com.example.novalinea.domain.model

data class MessageSend(
	val text: String,
	val from: String,
	val to: String,
	val type: TypesMessage,
	val project_id: String? = null,
	val project_title: String? = null,
	val project_price: Int? = null
)