package com.example.todoist.presentation.intent

sealed interface TodoMainScreenIntent {
    data object FetchAll : TodoMainScreenIntent
    data class MarkAsCompleted(val toDoId: Int,val isChecked: Boolean) : TodoMainScreenIntent
    data class Delete(val toDoId: Int) : TodoMainScreenIntent
    data object AddTodo : TodoMainScreenIntent
    data class EditTodoMainScreen(val toDoId: Int, val title: String, val description: String) : TodoMainScreenIntent
}