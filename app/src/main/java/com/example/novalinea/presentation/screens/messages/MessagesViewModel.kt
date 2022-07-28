package com.example.novalinea.presentation.screens.messages

import androidx.lifecycle.*
import com.example.novalinea.domain.model.ChatGet
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.use_case.GetChatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
	private val getChatsUseCase: GetChatsUseCase
): ViewModel() {

	private val _state = MutableLiveData<Response<List<ChatGet>>>()
	val state: LiveData<Response<List<ChatGet>>> get() = _state

	init {
		getChats()
	}

	fun getChats() {
		viewModelScope.launch {
			getChatsUseCase(
				setListChats = { listChats ->
					setListChats(listChats)
				}
			).collect { response ->
				_state.postValue(response)
			}
		}
	}

	private fun setListChats(listChats: List<ChatGet>) {
		_state.postValue(Response.Success(listChats as MutableList<ChatGet>))
	}
}