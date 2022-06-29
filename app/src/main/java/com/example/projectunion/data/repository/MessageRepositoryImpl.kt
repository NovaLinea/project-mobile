package com.example.projectunion.data.repository

import com.example.projectunion.data.realtimeDB.RealtimeDB
import com.example.projectunion.data.storage.Storage
import com.example.projectunion.domain.model.MessageSend
import com.example.projectunion.domain.repository.MessageRepository

class MessageRepositoryImpl(
	private val realtimeDB: RealtimeDB,
	private val storageDB: Storage
) : MessageRepository {

	override fun sendMessage(message: MessageSend) = realtimeDB.sendMessage(message)
}