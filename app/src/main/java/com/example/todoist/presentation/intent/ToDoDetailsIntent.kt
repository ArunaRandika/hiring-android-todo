package com.example.todoist.presentation.intent

sealed interface ToDoDetailsIntent {
    data class AddToDo(val title: String, val description: String) : ToDoDetailsIntent
    data class EditToDo( val title: String, val description: String) :
        ToDoDetailsIntent

    data object NavigateBack : ToDoDetailsIntent
}