package com.example.projectunion.data.realtimeDB

import android.util.Log
import com.example.projectunion.common.Constants.FROM_MESSAGE_FIELD
import com.example.projectunion.common.Constants.NODE_MESSAGES
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.common.Constants.TEXT_MESSAGE_FIELD
import com.example.projectunion.common.Constants.TIMESTAMP_MESSAGE_FIELD
import com.example.projectunion.common.Constants.TYPE_MESSAGE_FIELD
import com.example.projectunion.common.Constants.USER
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
			mapMessage[TEXT_MESSAGE_FIELD] = message.text
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

	override fun getMessages(id: String) = flow<Response<List<MessageGet>>> {
		try {
			emit(Response.Loading)
			val listMessages = mutableListOf<MessageGet>()

			val messages = db.child("$NODE_MESSAGES/${USER.id}/$id").get().await()
			messages.children.map { data ->
				data.children.map {
					val message = it.getValue(MessageGet::class.java)
					Log.d(TAG, message.toString())
					Log.d(TAG, it.value.toString())
				}
				Log.d(TAG, data.key.toString())
				/*val message = MessageGet(
					id = data.key.toString(),
					text = data.value.toString()
				)*/
			}

			emit(Response.Success(emptyList()))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun getChats(id: String) = flow<Response<List<Chat>>> {
		try {
			emit(Response.Loading)
			val listChats = mutableListOf<Chat>()

			val chats = db.child("$NODE_MESSAGES/$id").get().await()
			chats.children.map { data ->
				val chat = Chat(
					userId = data.key.toString()
				)
				data.children.map {
					Log.d(TAG, it.toString())
				}
				listChats.add(chat)
			}

			emit(Response.Success(listChats))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}
}
