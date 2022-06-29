package com.example.projectunion.presentation.screens.messages

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
	private val savedStateHandle: SavedStateHandle
): ViewModel() {

}