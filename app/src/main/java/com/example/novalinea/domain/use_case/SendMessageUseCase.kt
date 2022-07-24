package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.model.MessageSend
import com.example.novalinea.domain.repository.MessageRepository

class SendMessageUseCase(
	private val repository: MessageRepository
) {

	operator fun invoke(message: MessageSend) = repository.sendMessage(message)
}