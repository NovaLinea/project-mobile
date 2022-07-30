package com.example.novalinea.presentation.screens.chat

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.*
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.*
import com.example.novalinea.domain.use_case.GetMessagesUseCase
import com.example.novalinea.domain.use_case.SendMessageUseCase
import com.example.novalinea.presentation.screens.chat.components.MessageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
	private val getMessagesUseCase: GetMessagesUseCase,
	private val sendMessageUseCase: SendMessageUseCase,
	private val savedStateHandle: SavedStateHandle
): ViewModel() {

	val message by lazy { MessageState() }
	val messages = mutableStateListOf<MessageGet>()

	private val _stateGet = MutableLiveData<Response<List<MessageGet>>>()
	val stateGet: LiveData<Response<List<MessageGet>>> get() = _stateGet

	private val _stateSend = MutableLiveData<Response<Boolean>>()
	val stateSend: LiveData<Response<Boolean>> get() = _stateSend

	init {
		getMessages()
	}

	fun getMessages() {
		savedStateHandle.get<String>(ARGUMENT_USER_ID_KEY)?.let { userId ->
			viewModelScope.launch {
				getMessagesUseCase(
					id = userId,
					addItemMessage = { itemMessage ->
						addItemMessage(itemMessage)
					}
				).collect { response ->
					_stateGet.postValue(response)
				}
			}
		}
	}

	private fun addItemMessage(itemMessage: MessageGet?) {
		if (itemMessage != null) {
			messages.add(itemMessage)
			//Log.d(TAG, messages.size.toString())
			//_stateGet.postValue(Response.Success(messages))
		}
	}

	fun sendMessage() {
		savedStateHandle.get<String>(ARGUMENT_USER_ID_KEY)?.let { toID ->
			viewModelScope.launch {
				USER.id?.let { fromID ->
					val messageSend = MessageSend(
						text = message.text,
						from = fromID,
						to = toID,
						type = TypesMessage.TEXT
					)
					sendMessageUseCase(messageSend).collect { response ->
						_stateSend.postValue(response)
						message.text = ""
					}
				}
			}
		}
	}
}