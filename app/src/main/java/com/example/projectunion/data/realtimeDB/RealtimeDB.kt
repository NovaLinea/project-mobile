package com.example.projectunion.data.realtimeDB

import com.example.projectunion.domain.model.*
import kotlinx.coroutines.flow.Flow

interface RealtimeDB{

	// Message
	fun sendMessage(message: MessageSend): Flow<Response<Boolean>>
	fun getMessages(id: String): Flow<Response<List<MessageGet>>>
	fun getChats(id: String): Flow<Response<List<Chat>>>
}