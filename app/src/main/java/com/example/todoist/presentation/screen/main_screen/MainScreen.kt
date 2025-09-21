package com.example.todoist.presentation.screen.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoist.R
import com.example.todoist.presentation.components.CustomRoundedButton
import com.example.todoist.presentation.components.EmptyScreenScreen
import com.example.todoist.presentation.components.ErrorScreen
import com.example.todoist.presentation.components.LoadingScreen
import com.example.todoist.presentation.components.TodoListItem
import com.example.todoist.presentation.intent.TodoMainScreenIntent
import com.example.todoist.presentation.state.TodoState
import com.example.todoist.ui.theme.Teal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    todoState: TodoState,
    onEvent: (TodoMainScreenIntent) -> Unit,
    ) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )

    LaunchedEffect(true) {
        onEvent(TodoMainScreenIntent.FetchAll)
    }

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.headlineLarge)
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButton = {
            CustomRoundedButton(
                bgColor = Teal,
                onPressed = { onEvent(TodoMainScreenIntent.AddTodo)},
                size = 48.dp,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            when (todoState) {
                is TodoState.Loading -> {
                    LoadingScreen()
                }

                is TodoState.Success -> {
                    todoState.todos.forEach { item ->
                        TodoListItem(
                            item = item,
                            onChecked = { isChecked ->
                                item.id?.let { id ->
                                    onEvent(TodoMainScreenIntent.MarkAsCompleted(id, isChecked))
                                }

                            },
                            onDelete = {
                                item.id?.let { id ->
                                    onEvent(TodoMainScreenIntent.Delete(id))
                                }

                            },
                            onEdit = {
                                item.id?.let { id ->
                                    onEvent(TodoMainScreenIntent.EditTodoMainScreen(item.id, item.title, item.description))
                                }
                            }

                        )
                    }

                }

                is TodoState.Error -> {
                    ErrorScreen(
                        errorMessage = todoState.errorMessage
                    )
                }
                is TodoState.Empty -> {
                    EmptyScreenScreen(
                       message = stringResource(id = R.string.empty_msg)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        todoState = TodoState.Error("Preview error message"),
        onEvent = {},
    )
}
