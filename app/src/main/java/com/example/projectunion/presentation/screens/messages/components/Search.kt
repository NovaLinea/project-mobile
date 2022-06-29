package com.example.projectunion.presentation.screens.messages.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.R

@Composable
fun Search() {
	val searchText = remember {
		mutableStateOf("")
	}

	TextField(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(align = Alignment.CenterVertically)
			.height(65.dp)
			.padding(horizontal = 10.dp, vertical = 5.dp),
		value = searchText.value,
		onValueChange = {value -> searchText.value = value},
		placeholder = { Text(stringResource(id = R.string.search_field)) },
		singleLine = true,
		leadingIcon = { Icon(
			imageVector = Icons.Default.Search,
			contentDescription = "Search chat")
		},
		textStyle = TextStyle(fontSize = 16.sp),
		shape = RoundedCornerShape(10.dp),
		colors = TextFieldDefaults.textFieldColors(
			textColor = Color.Gray,
			disabledTextColor = Color.Transparent,
			backgroundColor = colorResource(id = R.color.app_background),
			focusedIndicatorColor = Color.Transparent,
			unfocusedIndicatorColor = Color.Transparent,
			disabledIndicatorColor = Color.Transparent
		)
	)
}