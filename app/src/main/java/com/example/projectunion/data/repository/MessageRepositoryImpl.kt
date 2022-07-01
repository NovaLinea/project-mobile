package com.example.projectunion.data.repository

import android.util.Log
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.data.firestoreDB.FirestoreDB
import com.example.projectunion.data.realtimeDB.RealtimeDB
import com.example.projectunion.data.storage.Storage
import com.example.projectunion.domain.model.Chat
import com.example.projectunion.domain.model.MessageGet
import com.example.projectunion.domain.model.MessageSend
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class MessageRepositoryImpl(
	private val realtimeDB: RealtimeDB,
	private val firestoreDB: FirestoreDB,
	private val storageDB: Storage
) : MessageRepository {

	override fun getChats(setListChats: (List<Chat>) -> Unit) = flow<Response<List<Chat>>> {
		try {
			emit(Response.Loading)

			realtimeDB.getChats(
				setListChats = { listChats ->
					setListChats(listChats)
					//getChatsDetail(listChats)
					/*listChats.forEachIndexed { index, chat ->
						val response = firestoreDB.getDetailChatById(chat.userId)
						Log.d(TAG, response.toString())
					}*/
				}
			).collect { response ->
				emit(response)
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	private suspend fun List<Chat>.asDetail(): List<Chat> {
		this.forEachIndexed { index, chat ->
			firestoreDB.getUserById(chat.userId).collect { response ->
				if (response is Response.Success) {
					this[index].userName = response.data?.name.toString()
					if (response.data?.photo != null)
						this[index].userPhoto = response.data.photo
				}
			}
		}
		return this
	}

	private fun getChatsDetail(
		listChats: List<Chat>,
		setListChats: (List<Chat>) -> Unit
	) = flow<Response<List<Chat>>> {
		Log.d(TAG, listChats.toString())
		listChats.forEachIndexed { index, chat ->
			firestoreDB.getUserById(chat.userId).collect { response ->
				when(response) {
					is Response.Success -> {
						Log.d(TAG, response.toString())
						listChats[index].userName = response.data?.name.toString()
						if (response.data?.photo != null)
							listChats[index].userPhoto = response.data.photo
						Log.d(TAG, listChats.toString())
						setListChats(listChats)
					}
					is Response.Error -> emit(response)
					is Response.Loading -> emit(response)
				}
			}
		}
	}

	override fun getMessages(id: String, setListMessages: (List<MessageGet?>) -> Unit) = realtimeDB.getMessages(id, setListMessages)

	override fun sendMessage(message: MessageSend) = realtimeDB.sendMessage(message)
}