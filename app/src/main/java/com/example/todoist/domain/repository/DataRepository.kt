package com.example.todoist.domain.repository

import com.example.todoist.domain.model.ToDoItem
import kotlinx.coroutines.flow.Flow

interface ToDoDataRepository {
    fun getToAllToDoData(): Flow<List<ToDoItem>>

    fun addToDo(title: String, description: String)

    fun markAsCompleted(id: Int, checked: Boolean)

    fun deleteToDo(id: Int)

    fun editToDo(id: Int, title: String, description: String)
}