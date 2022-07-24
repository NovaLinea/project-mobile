package com.example.novalinea.data.realtimeDB

import com.example.novalinea.domain.model.*
import kotlinx.coroutines.flow.Flow

interface RealtimeDB{

	fun getChats(setListChats: (List<Chat>) -> Unit): Flow<Response<List<Chat>>>
	fun getMessages(
		id: String,
		addItemMessage: (MessageGet?) -> Unit
	): Flow<Response<List<MessageGet>>>
	fun sendMessage(message: MessageSend): Flow<Response<Boolean>>
}