package com.example.todoist.presentation.screen.edit_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoist.R
import com.example.todoist.presentation.components.ErrorScreen
import com.example.todoist.presentation.components.LoadingScreen
import com.example.todoist.presentation.intent.ToDoDetailsIntent
import com.example.todoist.presentation.navigation.AddEditToDoNav
import com.example.todoist.presentation.state.AddEditState
import com.example.todoist.ui.theme.Teal
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    isFromEdit: Boolean,
    addEditToDoNavArg: AddEditToDoNav,
    addEditToDoState: AddEditState,
    onEvent: (ToDoDetailsIntent) -> Unit = {}
) {
    var title by remember { mutableStateOf(addEditToDoNavArg.title) }
    var description by remember { mutableStateOf(addEditToDoNavArg.description) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current


    var isComposableReady by remember { mutableStateOf(false) }

    val isButtonEnabled = title?.isNotBlank() == true && description?.isNotBlank() == true


    LaunchedEffect(isComposableReady) {
        if (isComposableReady) {
            try {
                delay(50)
                focusRequester.requestFocus()
                keyboardController?.show()
            } catch (_: Exception) {

            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            try {
                focusRequester.freeFocus()
                keyboardController?.hide()
            } catch (_: Exception) {

            }
        }
    }

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding()
            .fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(
                    text = if (isFromEdit) stringResource(id = R.string.edit_todo) else stringResource(
                        id = R.string.add_todo
                    )
                )
            })
        },
        bottomBar = {
            Button(
                onClick = {
                    if (isFromEdit) {
                        onEvent(
                            ToDoDetailsIntent.EditToDo(
                                title = title ?: "",
                                description = description ?: ""
                            )
                        )
                    } else {
                        onEvent(
                            ToDoDetailsIntent.AddToDo(
                                title = title ?: "",
                                description = description ?: ""
                            )
                        )
                    }

                },
                enabled = isButtonEnabled,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Teal,
                )
            ) {
                Text("Add")
            }
        },
        content = { padding ->
            when (addEditToDoState) {
                AddEditState.Empty -> {
                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .padding(16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top
                    ) {
                        isComposableReady = true
                        OutlinedTextField(
                            value = title ?: "",
                            onValueChange = { title = it },
                            label = { Text("Title") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = description ?: "",
                            onValueChange = { description = it },
                            label = { Text("Description") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            maxLines = 6,
                            singleLine = false,

                            )
                        Spacer(modifier = Modifier.height(24.dp))

                    }
                }

                is AddEditState.Error -> {
                    ErrorScreen(errorMessage = addEditToDoState.errorMessage)
                }

                AddEditState.Loading -> {
                    LoadingScreen()
                }

                AddEditState.Success -> {
                    onEvent(ToDoDetailsIntent.NavigateBack)

                }
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
fun EditScreenPreview() {
    EditScreen(
        onEvent = {},
        isFromEdit = false,
        addEditToDoState = AddEditState.Empty,
        addEditToDoNavArg = AddEditToDoNav(
            id = 0,
            title = "Sample Title",
            description = "Sample Description"
        )
    )
}
