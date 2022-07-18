package com.example.projectunion.presentation.screens.messages

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
import com.example.projectunion.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.projectunion.common.Constants.ARGUMENT_USER_NAME_KEY
import com.example.projectunion.common.Constants.ERROR_BY_GET_CHATS
import com.example.projectunion.common.Constants.MESSAGES_SCREEN
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.common.Constants.TITLE_NO_DIALOGS
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.error.Error
import com.example.projectunion.presentation.components.top_bar.TopBar
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.navigation.Router
import com.example.projectunion.presentation.screens.messages.components.ChatItem
import com.example.projectunion.presentation.screens.messages.components.ShimmerLoaderChats

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MessagesScreen(
	externalRouter: Router,
	viewModel: MessagesViewModel = hiltViewModel()
) {
	val state = viewModel.state.observeAsState(Response.Success(emptyList())).value
	val listState = rememberLazyListState()

	val scaffoldState = rememberScaffoldState()

	Scaffold(
		topBar = { TopBar(MESSAGES_SCREEN) },
		scaffoldState = scaffoldState
	) {
		when(state) {
			is Response.Loading -> ShimmerLoaderChats()
			is Response.Error -> {
				Log.d(TAG, state.message)
				Error(message = ERROR_BY_GET_CHATS)
			}
			is Response.Success -> {
				if (state.data.isNotEmpty()) {
					LazyColumn(
						state = listState,
						modifier = Modifier.fillMaxSize()
					) {
						itemsIndexed(state.data) {index,  chat ->
							ChatItem(
								userName = chat.userName,
								userPhoto = chat.userPhoto,
								lastMessage = chat.lastMessage,
								timestamp = chat.timestamp
							) {
								externalRouter.navigateTo(
									MainNavRoute.Chat.route
											+ "?${ARGUMENT_USER_ID_KEY}=${chat.userId}"
											+ "&${ARGUMENT_USER_NAME_KEY}=${chat.userName}"
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