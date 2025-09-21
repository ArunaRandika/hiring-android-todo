package com.example.todoist.data.repository

import com.example.todoist.domain.model.ToDoItem
import kotlinx.coroutines.flow.Flow

interface ToDoDataRepository {
    fun getToAllToDoData(): Flow<List<String>>

    fun addToDo(title: String, description: String)

    fun markAsCompleted(id: Int, checked: Boolean)

    fun deleteToDo(id: Int)

    fun editToDo(id: Int, title: String, description: String)
}