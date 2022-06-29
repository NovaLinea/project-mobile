package com.example.projectunion.data.realtimeDB

import com.example.projectunion.common.Constants.FROM_MESSAGE_FIELD
import com.example.projectunion.common.Constants.NODE_MESSAGES
import com.example.projectunion.common.Constants.TEXT_MESSAGE_FIELD
import com.example.projectunion.common.Constants.TIMESTAMP_MESSAGE_FIELD
import com.example.projectunion.common.Constants.USER
import com.example.projectunion.domain.model.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class RealtimeDBImpl(
	private val db: FirebaseDatabase
): RealtimeDB {

	override fun sendMessage(message: MessageSend) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)

			val refDialogUser = "$NODE_MESSAGES/${USER.id}/${message.from}"
			val refDialogReceivingUser = "$NODE_MESSAGES/${message.from}/${USER.id}"
			val messageKey = db.getReference(NODE_MESSAGES).child(refDialogUser).push().key

			message.timestamp = ServerValue.TIMESTAMP

			val mapMessage = hashMapOf<String, Any>()
			mapMessage[TEXT_MESSAGE_FIELD] = message.message
			mapMessage[FROM_MESSAGE_FIELD] = message.from
			mapMessage[TIMESTAMP_MESSAGE_FIELD] = ServerValue.TIMESTAMP

			val mapDialog = hashMapOf<String, Any>()
			mapDialog["$refDialogUser/$messageKey"] = mapMessage
			mapDialog["$refDialogReceivingUser/$messageKey"] = mapMessage

			db.getReference(NODE_MESSAGES).updateChildren(mapDialog).await()

			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}
}
