package com.example.projectunion.data.repository

import com.example.projectunion.data.firestoreDB.FirestoreDB
import com.example.projectunion.data.realtimeDB.RealtimeDB
import com.example.projectunion.data.storage.Storage
import com.example.projectunion.domain.model.Chat
import com.example.projectunion.domain.model.MessageGet
import com.example.projectunion.domain.model.MessageSend
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.repository.MessageRepository
import kotlinx.coroutines.flow.flow

class MessageRepositoryImpl(
	private val realtimeDB: RealtimeDB,
	private val firestoreDB: FirestoreDB,
	private val storageDB: Storage
) : MessageRepository {

	override fun sendMessage(message: MessageSend) = realtimeDB.sendMessage(message)

	override fun getMessages(id: String, setListMessages: (List<MessageGet?>) -> Unit) = realtimeDB.getMessages(id, setListMessages)

	override fun getChats(id: String) = flow<Response<List<Chat>>> {
		try {
			emit(Response.Loading)

			realtimeDB.getChats(id).collect { response ->
				when(response) {
					is Response.Loading -> emit(response)
					is Response.Success -> {
						response.data.forEachIndexed { index, chat ->
							firestoreDB.getUserById(chat.userId).collect { respUsr ->
								when(respUsr) {
									is Response.Success -> {
										response.data[index].userName = respUsr.data?.name.toString()
										if (respUsr.data?.photo != null)
											response.data[index].userPhoto = respUsr.data.photo
									}
									is Response.Error -> emit(respUsr)
									is Response.Loading -> emit(respUsr)
								}
							}
						}
						emit(Response.Success(response.data))
					}
					is Response.Error -> emit(response)
				}
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}
}