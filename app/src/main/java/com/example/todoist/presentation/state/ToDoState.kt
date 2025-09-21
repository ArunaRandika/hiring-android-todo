package com.example.todoist.presentation.state

import com.example.todoist.domain.model.ToDoItem

sealed class TodoState {
    data object Loading : TodoState()
    data class Success(val todos: List<ToDoItem>) : TodoState()
    data object Empty : TodoState()
    data class Error(val errorMessage: String) : TodoState()
}