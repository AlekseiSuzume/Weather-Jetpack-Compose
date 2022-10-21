package com.suzume.weatherjetpackcompose.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun BaseAppBar(
    searchBarState: SearchBarState,
    searchTextState: String,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
) {
    if (searchBarState == SearchBarState.CLOSED) {
        DefaultAppBar {
            onSearchTriggered()
        }
    } else {
        SearchAppBar(text = searchTextState,
            onTextChanged = { onTextChanged(it) },
            onCloseClicked = { onCloseClicked() },
            onSearchClicked = { onSearchClicked(it) })
    }

}

@Composable
private fun DefaultAppBar(onSearchTriggered: () -> Unit) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
        title = { },
        actions = {
            IconButton(onClick = { onSearchTriggered() }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
private fun SearchAppBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.Transparent),
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White
            ),
            keyboardActions = KeyboardActions {
                onSearchClicked(text)
            },
            onValueChange = {
                onTextChanged(it)
            },
            placeholder = {
                Text(
                    text = "Введите название города...",
                    color = Color.White.copy(alpha = 0.5f)
                )
            },
            leadingIcon = {
                IconButton(onClick = {
                    onSearchClicked(text)
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon",
                        tint = Color.White.copy(alpha = 0.5f)
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        onTextChanged("")
                    } else {
                        onCloseClicked()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close icon",
                        tint = Color.White
                    )
                }
            }
        )
    }
}
