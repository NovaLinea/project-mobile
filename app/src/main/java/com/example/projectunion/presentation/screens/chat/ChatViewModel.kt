package com.example.projectunion.presentation.screens.chat

import android.util.Log
import androidx.lifecycle.*
import com.example.projectunion.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.common.Constants.TYPE_MESSAGE_TEXT
import com.example.projectunion.common.Constants.USER
import com.example.projectunion.domain.model.*
import com.example.projectunion.domain.use_case.GetMessagesUseCase
import com.example.projectunion.domain.use_case.GetUserByIdUseCase
import com.example.projectunion.domain.use_case.SendMessageUseCase
import com.example.projectunion.presentation.screens.chat.components.MessageState
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
	val messages = mutableListOf<MessageGet>()

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
					setListMessages = { listMessages ->
						setListMessages(listMessages)
					},
					addItemMessage = { itemMessage ->
						addItemMessage(itemMessage)
					}
				).collect { response ->
					_stateGet.postValue(response)
				}
			}
		}
	}

	private fun setListMessages(listMessages: List<MessageGet?>) {
		_stateGet.postValue(Response.Success(listMessages as MutableList<MessageGet>))
	}

	private fun addItemMessage(itemMessage: MessageGet?) {
		if (itemMessage != null) {
			messages.add(itemMessage)
		}
		//_stateGet.postValue(Response.Success(messages))
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