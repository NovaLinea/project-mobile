package com.example.novalinea.presentation.screens.chat

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.*
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.common.Constants.TYPE_MESSAGE_TEXT
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.*
import com.example.novalinea.domain.use_case.GetMessagesUseCase
import com.example.novalinea.domain.use_case.GetUserByIdUseCase
import com.example.novalinea.domain.use_case.SendMessageUseCase
import com.example.novalinea.presentation.screens.chat.components.MessageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
	private val getUserByIdUseCase: GetUserByIdUseCase,
	private val getMessagesUseCase: GetMessagesUseCase,
	private val sendMessageUseCase: SendMessageUseCase,
	private val savedStateHandle: SavedStateHandle
): ViewModel() {

	val message by lazy { MessageState() }
	val messages = mutableStateListOf<MessageGet>()

	private val _statePhoto = MutableLiveData<String?>()
	val statePhoto: LiveData<String?> get() = _statePhoto

	private val _stateGet = MutableLiveData<Response<List<MessageGet>>>()
	val stateGet: LiveData<Response<List<MessageGet>>> get() = _stateGet

	private val _stateSend = MutableLiveData<Response<Boolean>>()
	val stateSend: LiveData<Response<Boolean>> get() = _stateSend

	init {
		getDataUser()
		getMessages()
	}

	private fun getDataUser() {
		savedStateHandle.get<String>(ARGUMENT_USER_ID_KEY)?.let { userId ->
			viewModelScope.launch {
				getUserByIdUseCase(userId).collect { response ->
					if (response is Response.Error)
						Log.d(TAG, response.message)
					else if (response is Response.Success) {
						_statePhoto.postValue(response.data?.photo)
					}
				}
			}
		}
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
			Log.d(TAG, messages.size.toString())
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
						type = TYPE_MESSAGE_TEXT
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