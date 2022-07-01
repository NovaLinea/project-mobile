package com.example.projectunion.data.realtimeDB

import com.example.projectunion.common.Constants.FROM_MESSAGE_FIELD
import com.example.projectunion.common.Constants.NODE_MESSAGES
import com.example.projectunion.common.Constants.TEXT_MESSAGE_FIELD
import com.example.projectunion.common.Constants.TIMESTAMP_MESSAGE_FIELD
import com.example.projectunion.common.Constants.TYPE_MESSAGE_FIELD
import com.example.projectunion.common.Constants.USER
import com.example.projectunion.domain.model.*
import com.google.firebase.database.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class RealtimeDBImpl(
	private val db: DatabaseReference
): RealtimeDB {

	override fun getChats(id: String) = flow<Response<List<Chat>>> {
		try {
			emit(Response.Loading)
			val listChats = mutableListOf<Chat>()

			val chats = db.child("$NODE_MESSAGES/$id").get().await()
			chats.children.map { chat ->
				val lastMessageData = chat.children.last().getValue(MessageGet::class.java)
				val chatItem = Chat(
					userId = chat.key.toString(),
					lastMessage = lastMessageData!!.text,
					timestamp = lastMessageData.timestamp
				)
				listChats.add(chatItem)
			}

			emit(Response.Success(listChats))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun getMessages(
		id: String,
		setListMessages: (List<MessageGet?>) -> Unit
	) = flow<Response<List<MessageGet>>> {
		try {
			emit(Response.Loading)

			db.child("$NODE_MESSAGES/${USER.id}/$id")
				.addValueEventListener(object: ValueEventListener {
					override fun onDataChange(snapshot: DataSnapshot) {
						val messages = snapshot.children.map { data ->
							val message = data.getValue(MessageGet::class.java)
							if (message != null) {
								message.id =  data.key.toString()
							}
							message
						}
						setListMessages(messages)
					}

					override fun onCancelled(error: DatabaseError) {}
				})
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

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
}
