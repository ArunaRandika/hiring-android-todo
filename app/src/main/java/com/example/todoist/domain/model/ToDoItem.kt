package com.example.todoist.domain.model

data class ToDoItem(
    val id: Int?,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)
