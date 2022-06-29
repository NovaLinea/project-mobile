package com.example.projectunion.presentation.screens.chat

import androidx.lifecycle.*
import com.example.projectunion.common.Constants
import com.example.projectunion.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.projectunion.common.Constants.USER
import com.example.projectunion.domain.model.MessageSend
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.use_case.SendMessageUseCase
import com.example.projectunion.presentation.screens.chat.components.MessageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
	private val sendMessageUseCase: SendMessageUseCase,
	private val savedStateHandle: SavedStateHandle
): ViewModel() {

	val message by lazy { MessageState() }

	private val _state = MutableLiveData<Response<Boolean>>()
	val state: LiveData<Response<Boolean>> get() = _state

	fun sendMessage() {
		savedStateHandle.get<String>(ARGUMENT_USER_ID_KEY)?.let { userID ->
			if (userID != "-1") {
				viewModelScope.launch {
					USER.id?.let { id ->
						val messageSend = MessageSend(message = message.text, from = id, to = userID, type = "text")
						sendMessageUseCase(messageSend).collect { response ->
							_state.postValue(response)
							message.text = ""
						}
					}
				}
			}
		}
	}
}