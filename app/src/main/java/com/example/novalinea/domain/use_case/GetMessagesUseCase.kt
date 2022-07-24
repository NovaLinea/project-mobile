package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.model.MessageGet
import com.example.novalinea.domain.repository.MessageRepository

class GetMessagesUseCase(
	private val repository: MessageRepository
) {

	operator fun invoke(
		id: String,
		addItemMessage: (MessageGet?) -> Unit
	) = repository.getMessages(id, addItemMessage)
}