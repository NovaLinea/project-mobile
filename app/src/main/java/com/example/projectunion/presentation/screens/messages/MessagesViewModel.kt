package com.example.projectunion.presentation.screens.messages

import androidx.lifecycle.*
import com.example.projectunion.domain.model.Chat
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.use_case.GetChatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
	private val getChatsUseCase: GetChatsUseCase
): ViewModel() {

	private val _state = MutableLiveData<Response<List<Chat>>>()
	val state: LiveData<Response<List<Chat>>> get() = _state

	init {
		getChats()
	}

	private fun getChats() {
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

	private fun setListChats(listChats: List<Chat>) {
		//listChats.sortedByDescending { it.timestamp.toString().asTime() }
		//Log.d(TAG, listChats.toString())
		_state.postValue(Response.Success(listChats as MutableList<Chat>))
	}
}