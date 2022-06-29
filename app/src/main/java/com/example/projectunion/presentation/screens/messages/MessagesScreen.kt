package com.example.projectunion.presentation.screens.messages

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectunion.common.Constants.MESSAGES_SCREEN
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.common.Constants.TITLE_NO_DIALOGS
import com.example.projectunion.domain.model.Response
import com.example.projectunion.presentation.components.loader.Loader
import com.example.projectunion.presentation.components.top_bar.TopBar
import com.example.projectunion.presentation.navigation.Router

@Composable
fun MessagesScreen(
	externalRouter: Router,
	viewModel: MessagesViewModel = hiltViewModel()
) {
	val state = viewModel.state.observeAsState(Response.Success(emptyList())).value
	val listState = rememberLazyListState()

	Scaffold(
		topBar = { TopBar(MESSAGES_SCREEN) },
	) {
		when(state) {
			is Response.Loading -> Loader()
			is Response.Success -> {
				if (state.data.isNotEmpty()) {
					LazyColumn(
						state = listState,
						modifier = Modifier.fillMaxSize()
					) {
						items(state.data) { chat ->
							Log.d(TAG, chat.userId)
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
			is Response.Error -> Log.d(TAG, state.message)
		}
	}
}