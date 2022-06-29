package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.repository.MessageRepository

class GetChatsUseCase(
	private val repository: MessageRepository
) {

	operator fun invoke(id: String) = repository.getChats(id)
}