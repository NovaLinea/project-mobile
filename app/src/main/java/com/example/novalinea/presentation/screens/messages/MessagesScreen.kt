package com.example.novalinea.presentation.screens.messages

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.novalinea.common.Constants.ARGUMENT_CHAT_DATA
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.ERROR_BY_GET_CHATS
import com.example.novalinea.common.Constants.MESSAGES_SCREEN
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.common.Constants.TITLE_NO_DIALOGS
import com.example.novalinea.domain.model.ChatOpen
import com.example.novalinea.domain.model.Response
import com.example.novalinea.presentation.components.error.Error
import com.example.novalinea.presentation.components.top_bar.TopBar
import com.example.novalinea.presentation.navigation.MessagesNavRoute
import com.example.novalinea.presentation.navigation.Router
import com.example.novalinea.presentation.screens.messages.components.ChatItem
import com.example.novalinea.presentation.screens.messages.components.ShimmerLoaderChats

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MessagesScreen(
	externalRouter: Router,
	viewModel: MessagesViewModel = hiltViewModel()
) {
	val state = viewModel.state.observeAsState(Response.Success(emptyList())).value
	val listState = rememberLazyListState()

	Scaffold(
		topBar = { TopBar(MESSAGES_SCREEN) }
	) {
		when(state) {
			is Response.Loading -> ShimmerLoaderChats()
			is Response.Error -> {
				Log.d(TAG, state.message)
				Error(message = ERROR_BY_GET_CHATS) {
					viewModel.getChats()
				}
			}
			is Response.Success -> {
				if (state.data.isNotEmpty()) {
					LazyColumn(
						state = listState,
						modifier = Modifier.fillMaxSize()
					) {
						itemsIndexed(
							items = state.data.sortedByDescending { chat ->
								chat.timestamp.toString()
							}
						) {index, chat ->
							ChatItem(
								userName = chat.userName,
								userPhoto = chat.userPhoto,
								lastMessage = chat.lastMessage,
								timestamp = chat.timestamp,
								viewed = chat.viewed,
								countNewMessages = chat.countNewMessages
							) {
								externalRouter.routeTo(
									MessagesNavRoute.Chat.route
											+ "?${ARGUMENT_USER_ID_KEY}=${chat.userId}",
									Pair(
										ARGUMENT_CHAT_DATA,
										ChatOpen(
											userId = chat.userId,
											userName = chat.userName,
											userPhoto = chat.userPhoto
										)
									)
								)
							}

							if (index != state.data.lastIndex) {
								Row(
									modifier = Modifier.fillMaxWidth(),
									horizontalArrangement = Arrangement.End
								) {
									Spacer(
										modifier = Modifier
											.height(1.dp)
											.fillMaxWidth(0.8f)
											.background(Color.LightGray)
									)
								}
							}
						}
					}
				}
				else {
					Column(
						modifier = Modifier.fillMaxSize(),
						horizontalAlignment = Alignment.CenterHorizontally
					) {
						Text(
							modifier = Modifier.padding(top = 170.dp),
							text = TITLE_NO_DIALOGS,
							style = MaterialTheme.typography.body2
						)
					}
				}
			}
		}
	}
}