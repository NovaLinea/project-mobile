package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.model.Chat
import com.example.novalinea.domain.repository.MessageRepository

class GetChatsUseCase(
	private val repository: MessageRepository
) {

	operator fun invoke(setListChats: (List<Chat>) -> Unit) = repository.getChats(setListChats)
}