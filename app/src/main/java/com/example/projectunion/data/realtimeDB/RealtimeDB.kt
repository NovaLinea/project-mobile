package com.example.projectunion.data.realtimeDB

import com.example.projectunion.domain.model.*
import kotlinx.coroutines.flow.Flow

interface RealtimeDB{

	// Message
	fun sendMessage(message: MessageSend): Flow<Response<Boolean>>
	fun getChats(id: String): Flow<Response<List<Chat>>>
}