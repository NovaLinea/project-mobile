package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.model.MessageSend
import com.example.projectunion.domain.repository.MessageRepository

class SendMessageUseCase(
	private val repository: MessageRepository
) {

	operator fun invoke(message: MessageSend) = repository.sendMessage(message)
}