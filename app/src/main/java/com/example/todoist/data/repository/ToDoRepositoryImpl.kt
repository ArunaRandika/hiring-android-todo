package com.example.todoist.data.repository

import com.example.todoist.domain.model.ToDoItem
import com.example.todoist.domain.repository.ToDoDataRepository
import com.example.todoist.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val toDoDataRepository: ToDoDataRepository
) : ToDoRepository {
    override suspend fun getTodos(): Flow<Result<List<ToDoItem>>> = flow {
        try {
            toDoDataRepository.getToAllToDoData().collect { todos ->
                emit(Result.success(todos))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun addTodo(
        title: String,
        description: String
    ): Flow<Result<Unit>> = flow {
        try {
            toDoDataRepository.addToDo(title, description)
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun markAsCompleted(
        id: Int,
        checked: Boolean
    ): Flow<Result<Unit>> = flow {
        try {
            toDoDataRepository.markAsCompleted(id, checked)
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun deleteItem(id: Int): Flow<Result<Unit>> = flow {
        try {
            toDoDataRepository.deleteToDo(id)
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(Exception(e)))
        }
    }


    override fun editItem(
        id: Int,
        title: String,
        description: String
    ): Flow<Result<Unit>> = flow {
        try {
            toDoDataRepository.editToDo(id, title, description)
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(Exception(e)))
        }
    }

}