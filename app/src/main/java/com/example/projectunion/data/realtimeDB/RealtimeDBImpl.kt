package com.example.projectunion.data.realtimeDB

import com.example.projectunion.common.Constants.FROM_MESSAGE_FIELD
import com.example.projectunion.common.Constants.NODE_MESSAGES
import com.example.projectunion.common.Constants.TEXT_MESSAGE_FIELD
import com.example.projectunion.common.Constants.TIMESTAMP_MESSAGE_FIELD
import com.example.projectunion.common.Constants.TYPE_MESSAGE_FIELD
import com.example.projectunion.domain.model.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ServerValue
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class RealtimeDBImpl(
	private val db: DatabaseReference
): RealtimeDB {

	override fun sendMessage(message: MessageSend) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)

			val refFromUser = "$NODE_MESSAGES/${message.from}/${message.to}"
			val refToUser = "$NODE_MESSAGES/${message.to}/${message.from}"
			val messageKey = db.child(refFromUser).push().key

			val mapMessage = hashMapOf<String, Any>()
			mapMessage[TEXT_MESSAGE_FIELD] = message.message
			mapMessage[FROM_MESSAGE_FIELD] = message.from
			mapMessage[TYPE_MESSAGE_FIELD] = message.type
			mapMessage[TIMESTAMP_MESSAGE_FIELD] = ServerValue.TIMESTAMP

			val mapDialog = hashMapOf<String, Any>()
			mapDialog["$refFromUser/$messageKey"] = mapMessage
			mapDialog["$refToUser/$messageKey"] = mapMessage

			db.updateChildren(mapDialog).await()
			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun getChats(id: String) = flow<Response<List<Chat>>> {
		try {
			emit(Response.Loading)
			val listChats = mutableListOf<Chat>()

			val chats = db.child("$NODE_MESSAGES/$id").get().await()
			chats.children.forEach { data ->
				val chat = Chat(
					userId = data.key.toString()
				)
				listChats.add(chat)
			}

			emit(Response.Success(listChats))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}
}
