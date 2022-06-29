package com.example.projectunion.presentation.screens.chat

import androidx.lifecycle.*
import com.example.projectunion.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.projectunion.common.Constants.TYPE_MESSAGE_TEXT
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

	private val _stateSend = MutableLiveData<Response<Boolean>>()
	val stateSend: LiveData<Response<Boolean>> get() = _stateSend

	fun sendMessage() {
		savedStateHandle.get<String>(ARGUMENT_USER_ID_KEY)?.let { toID ->
			viewModelScope.launch {
				USER.id?.let { fromID ->
					val messageSend = MessageSend(
						message = message.text,
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