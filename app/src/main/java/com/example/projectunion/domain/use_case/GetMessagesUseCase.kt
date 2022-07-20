package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.model.MessageGet
import com.example.projectunion.domain.repository.MessageRepository

class GetMessagesUseCase(
	private val repository: MessageRepository
) {

	operator fun invoke(
		id: String,
		addItemMessage: (MessageGet?) -> Unit
	) = repository.getMessages(id, addItemMessage)
}