package com.example.novalinea.data.realtimeDB

import com.example.novalinea.domain.model.*
import kotlinx.coroutines.flow.Flow

interface RealtimeDB{

	fun getChats(setListChats: (List<ChatGet>) -> Unit): Flow<Response<List<ChatGet>>>
	fun getMessages(
		id: String,
		addItemMessage: (MessageGet?) -> Unit
	): Flow<Response<List<MessageGet>>>
	fun sendMessage(message: MessageSend): Flow<Response<Boolean>>
}