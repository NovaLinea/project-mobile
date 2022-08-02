package com.example.novalinea.domain.model

data class MessageGet(
	var id: String = "",
	val text: String = "",
	val from: String = "",
	val type: TypesMessage = TypesMessage.TEXT,
	val project_id: String = "",
	val project_title: String = "",
	val project_price: Int? = null,
	val team_staff: String = "",
	val timestamp: Any = "",
	val viewed: Boolean = false
)