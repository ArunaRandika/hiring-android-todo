package com.example.todoist.data.mapper

import com.example.todoist.data.model.TodoEntity
import com.example.todoist.domain.model.ToDoItem

fun TodoEntity.toTodoItem(): ToDoItem {
    return ToDoItem(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
    )
}