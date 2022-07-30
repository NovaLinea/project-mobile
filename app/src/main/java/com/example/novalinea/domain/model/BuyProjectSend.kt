package com.example.novalinea.domain.model

data class BuyProjectSend(
	val from: String,
	val to: String,
	val type: TypesMessage = TypesMessage.BUY_PROJECT,
	val project_id: String,
	val project_title: String,
	val project_price: Int
)