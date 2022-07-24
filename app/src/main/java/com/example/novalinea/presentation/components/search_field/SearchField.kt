package com.example.novalinea.presentation.components.search_field

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.novalinea.common.Constants.SEARCH_PLACEHOLDER

@Composable
fun SearchField(
	value: String,
	onValueChange: (String) -> Unit,
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.height(45.dp),
		elevation = 3.dp,
		shape = RoundedCornerShape(5.dp)
	) {
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.CenterStart
		) {
			Icon(
				modifier = Modifier.padding(start = 10.dp),
				imageVector = Icons.Default.Search,
				contentDescription = null,
				tint = Color.DarkGray
			)

			BasicTextField(
				modifier = Modifier.padding(start = 40.dp, end = 5.dp),
				value = value,
				onValueChange = { value -> onValueChange(value) },
				singleLine = true,
				textStyle = MaterialTheme.typography.body2,
				decorationBox = { innerTextField ->
					Row() {
						if (value.isEmpty()) {
							Text(
								text = SEARCH_PLACEHOLDER,
								style = MaterialTheme.typography.body2,
								color = Color.DarkGray
							)
						}
					}
					innerTextField()
				}
			)
		}
	}
}