package com.example.projectunion.domain.repository

import com.example.projectunion.domain.model.Chat
import com.example.projectunion.domain.model.MessageGet
import com.example.projectunion.domain.model.MessageSend
import com.example.projectunion.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

	fun getChats(setListChats: (List<Chat>) -> Unit): Flow<Response<List<Chat>>>
	//fun getChatsDetail(listChats: List<Chat>, setListChats: (List<Chat>) -> Unit): Flow<Response<List<Chat>>>
	fun getMessages(id: String,
					setListMessages: (List<MessageGet?>) -> Unit,
					addItemMessage: (MessageGet?) -> Unit): Flow<Response<List<MessageGet>>>
	fun sendMessage(message: MessageSend): Flow<Response<Boolean>>
}