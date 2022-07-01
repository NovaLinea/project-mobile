package com.example.projectunion.data.repository

import android.util.Log
import androidx.compose.runtime.rememberCoroutineScope
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.data.firestoreDB.FirestoreDB
import com.example.projectunion.data.realtimeDB.RealtimeDB
import com.example.projectunion.data.storage.Storage
import com.example.projectunion.domain.model.Chat
import com.example.projectunion.domain.model.MessageGet
import com.example.projectunion.domain.model.MessageSend
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.repository.MessageRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
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
				when(response) {
					is Response.Loading -> Log.d(TAG, "Loader")
					is Response.Error -> Log.d(TAG, response.message)
					is Response.Success -> {
						listChats[index].userName = response.data?.name.toString()
						listChats[index].userPhoto = response.data?.photo.toString()
					}
				}
			}
		}
		setListChats(listChats)
	}

	override fun getMessages(id: String, setListMessages: (List<MessageGet?>) -> Unit) = realtimeDB.getMessages(id, setListMessages)

	override fun sendMessage(message: MessageSend) = realtimeDB.sendMessage(message)
}