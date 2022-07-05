package com.example.projectunion.data.realtimeDB

import android.util.Log
import com.example.projectunion.common.Constants.FROM_MESSAGE_FIELD
import com.example.projectunion.common.Constants.NODE_MESSAGES
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.common.Constants.TEXT_MESSAGE_FIELD
import com.example.projectunion.common.Constants.TIMESTAMP_MESSAGE_FIELD
import com.example.projectunion.common.Constants.TYPE_MESSAGE_FIELD
import com.example.projectunion.common.Constants.USER
import com.example.projectunion.common.Constants.countMessagesChat
import com.example.projectunion.domain.model.*
import com.google.firebase.database.*
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class RealtimeDBImpl(
	private val db: DatabaseReference
): RealtimeDB {

	override fun getChats(
		setListChats: (List<Chat>) -> Unit
	): Flow<Response<List<Chat>>> = flow<Response<List<Chat>>> {
		try {
			emit(Response.Loading)

			db.child("$NODE_MESSAGES/${USER.id}")
				.addValueEventListener(object: ValueEventListener {
					override fun onDataChange(snapshot: DataSnapshot) {
						val chats = snapshot.children.map { chat ->
							val lastMessage = chat.children.last().getValue(MessageGet::class.java)
							val chatItem = Chat(
								userId = chat.key.toString(),
								lastMessage = lastMessage!!.text,
								timestamp = lastMessage.timestamp
							)
							chatItem
						}
						setListChats(chats)
					}

					override fun onCancelled(error: DatabaseError) {}
				})
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun getMessages(
		id: String,
		setListMessages: (List<MessageGet?>) -> Unit,
		addItemMessage: (MessageGet?) -> Unit
	) = flow<Response<List<MessageGet>>> {
		try {
			emit(Response.Loading)

			db.child("$NODE_MESSAGES/${USER.id}/$id")
				//.limitToLast(countMessagesChat)
				/*.addChildEventListener(object: ChildEventListener {

					override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
						val message = snapshot.getValue(MessageGet::class.java)
						if (message != null) {
							message.id =  snapshot.key.toString()
						}
						addItemMessage(message)
					}

					override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
					override fun onChildRemoved(snapshot: DataSnapshot) {}
					override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
					override fun onCancelled(error: DatabaseError) {}

				})*/
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
