package com.example.projectunion.domain.repository

import com.example.projectunion.domain.model.Chat
import com.example.projectunion.domain.model.MessageSend
import com.example.projectunion.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

	fun sendMessage(message: MessageSend): Flow<Response<Boolean>>
	fun getChats(id: String): Flow<Response<List<Chat>>>
}