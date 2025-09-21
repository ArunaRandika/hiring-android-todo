package com.example.todoist.domain.repository

import com.example.todoist.domain.model.ToDoItem
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    suspend fun getTodos(): Flow<Result<List<ToDoItem>>>

    suspend fun addTodo(title: String,description: String): Flow<Result<Unit>>

    suspend fun markAsCompleted(id: Int, checked: Boolean): Flow<Result<Unit>>

    fun deleteItem(id: Int):Flow<Result<Unit>>

    fun editItem(id: Int, title: String, description: String):Flow<Result<Unit>>
}