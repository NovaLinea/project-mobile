package com.example.projectunion.data.realtimeDB

import com.example.projectunion.domain.model.*
import kotlinx.coroutines.flow.Flow

interface RealtimeDB{

	// Message
	fun getChats(id: String): Flow<Response<List<Chat>>>
	fun getMessages(id: String, setListMessages: (List<MessageGet?>) -> Unit): Flow<Response<List<MessageGet>>>
	fun sendMessage(message: MessageSend): Flow<Response<Boolean>>
}