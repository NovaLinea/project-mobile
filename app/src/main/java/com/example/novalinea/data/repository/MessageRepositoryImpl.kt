package com.example.novalinea.data.repository

import com.example.novalinea.data.firestoreDB.FirestoreDB
import com.example.novalinea.data.realtimeDB.RealtimeDB
import com.example.novalinea.data.storage.Storage
import com.example.novalinea.domain.model.Chat
import com.example.novalinea.domain.model.MessageGet
import com.example.novalinea.domain.model.MessageSend
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.repository.MessageRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MessageRepositoryImpl(
	private val realtimeDB: RealtimeDB,
	private val firestoreDB: FirestoreDB,
	private val storageDB: Storage
) : MessageRepository {

	@OptIn(DelicateCoroutinesApi::class)
	override fun getChats(setListChats: (List<Chat>) -> Unit) = flow<Response<List<Chat>>> {
		try {
			emit(Response.Loading)

			realtimeDB.getChats(
				setListChats = { listChats ->
					GlobalScope.launch(Dispatchers.IO) {
						getChatsDetail(listChats, setListChats)
					}
				}
			).collect { response ->
				emit(response)
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	private suspend fun getChatsDetail(
		listChats: List<Chat>,
		setListChats: (List<Chat>) -> Unit
	) {
		listChats.forEachIndexed { index, chat ->
			firestoreDB.getUserById(chat.userId).collect { response ->
				if (response is Response.Success) {
					listChats[index].userName = response.data?.name.toString()
					listChats[index].userPhoto = response.data?.photo.toString()
				}
			}
		}
		setListChats(listChats)
	}

	override fun getMessages(
		id: String,
		addItemMessage: (MessageGet?) -> Unit
	) = realtimeDB.getMessages(id, addItemMessage)

	override fun sendMessage(message: MessageSend) = realtimeDB.sendMessage(message)
}