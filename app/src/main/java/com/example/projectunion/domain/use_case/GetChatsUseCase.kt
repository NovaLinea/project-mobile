package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.model.Chat
import com.example.projectunion.domain.repository.MessageRepository

class GetChatsUseCase(
	private val repository: MessageRepository
) {

	operator fun invoke(setListChats: (List<Chat>) -> Unit) = repository.getChats(setListChats)
}