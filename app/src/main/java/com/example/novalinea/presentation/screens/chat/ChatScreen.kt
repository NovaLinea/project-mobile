package com.example.novalinea.presentation.screens.chat

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.novalinea.R
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.ERROR_BY_GET_MESSAGES
import com.example.novalinea.common.Constants.ERROR_BY_SEND_MESSAGE
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.common.Constants.TITLE_NO_MESSAGES
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.domain.model.ChatOpen
import com.example.novalinea.domain.model.Response
import com.example.novalinea.presentation.components.error.Error
import com.example.novalinea.presentation.components.loader.Loader
import com.example.novalinea.presentation.navigation.BottomNavRoute
import com.example.novalinea.presentation.screens.chat.components.ChatTopBar
import com.example.novalinea.presentation.screens.chat.components.MessageField
import com.example.novalinea.presentation.screens.chat.components.MessageItem
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ChatScreen(
	chat: ChatOpen,
	navController: NavController,
	viewModel: ChatViewModel = hiltViewModel()
) {
	val stateGet = viewModel.stateGet.observeAsState(Response.Success(emptyList())).value
	val stateSend = viewModel.stateSend.observeAsState(Response.Success(false)).value

	val scaffoldState = rememberScaffoldState()
	val listState = rememberLazyListState()
	val scope = rememberCoroutineScope()
	var scrollToLastMessage = true
	//var isScrolling = false

	Scaffold(
		topBar = {
			chat.userName?.let { name ->
				ChatTopBar(
					title = name,
					photo = chat.userPhoto,
					onClickBack = { navController.popBackStack() },
					onClickUser = {
						navController.navigate(
							BottomNavRoute.Profile.route + "?${ARGUMENT_USER_ID_KEY}=${chat.userId}"
						)
					}
				)
			}
		},
		bottomBar = {
			MessageField(
				value = viewModel.message.text,
				onValueChange = {
					viewModel.message.text = it
				},
				onSend = {
					if (viewModel.message.text.isNotEmpty()) {
						scrollToLastMessage = true
						viewModel.sendMessage()
					}
				}
			)
		},
		scaffoldState = scaffoldState,
		snackbarHost = {
			SnackbarHost(it) { data ->
				Snackbar(
					backgroundColor = Color.White,
					contentColor = Color.Black,
					snackbarData = data
				)
			}
		}
	) { innerPadding ->
		if (stateSend is Response.Error) {
			Log.d(TAG, stateSend.message)
			scope.launch {
				scaffoldState.snackbarHostState.showSnackbar(ERROR_BY_SEND_MESSAGE)
			}
		}

		if (stateGet is Response.Loading)
			Loader()
		else if (stateGet is Response.Error){
			Log.d(TAG, stateGet.message)
			Error(
				message = ERROR_BY_GET_MESSAGES,
				background = colorResource(id = R.color.app_background),
				onClickTryAgain = {
					viewModel.getMessages()
				}
			)
		}

		Box(
			modifier = Modifier.padding(innerPadding)
		) {
			if (viewModel.messages.isNotEmpty()) {
				if (scrollToLastMessage) {
					scope.launch {
						listState.animateScrollToItem(viewModel.messages.lastIndex)
						//scrollToLastMessage = false
					}
				}
				LazyColumn(
					state = listState,
					modifier = Modifier
						.fillMaxSize()
						.background(colorResource(id = R.color.app_background)),
					contentPadding = PaddingValues(all = 10.dp),
					verticalArrangement = Arrangement.spacedBy(10.dp)
				) {
					/*if (listState.isScrollInProgress && listState.firstVisibleItemIndex == 0) {
						isScrolling = true
					}

					if (isScrolling) {
						isScrolling = false
						LIMIT_MESSAGES_CHAT += 10
						viewModel.getMessages()
					}*/

					items(
						items = viewModel.messages,
						key = { message ->
							message.id
						}
					) { message ->
						var location = Arrangement.Start
						if (USER.id == message.from)
							location = Arrangement.End

						//Log.d(TAG, listState.)

						MessageItem(
							message = message.text,
							time = message.timestamp,
							viewed = message.viewed,
							locationArrangement = location
						)
					}
				}
			}
			else {
				Column(
					modifier = Modifier
						.fillMaxSize()
						.background(colorResource(id = R.color.app_background)),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					Text(
						modifier = Modifier.padding(top = 170.dp),
						text = TITLE_NO_MESSAGES,
						style = MaterialTheme.typography.body2
					)
				}
			}
		}
	}
}