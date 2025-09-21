package com.example.todoist.data.repository

import com.example.todoist.data.model.TodoEntity
import com.example.todoist.data.storage.dao.TodoDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ToDoDataRepositoryImpl @Inject constructor(
    private val toDoDao: TodoDao
) : ToDoDataRepository {
    override fun getToAllToDoData(): Flow<List<String>> {
        return toDoDao.getAllToDoItems().map { cachedTodos ->
            cachedTodos.map { cachedTodo ->
               ""
            }
        }
    }

    override fun addToDo(title: String, description: String) {
        toDoDao.saveToDo(
            todo = TodoEntity(
                title = title,
                description = description,
                isCompleted = false
            )
        )
    }

    override fun markAsCompleted(id: Int, checked: Boolean) {
        toDoDao.markAsCompleted(id, checked)
    }

    override fun deleteToDo(id: Int) {
        toDoDao.deleteToDo(id)
    }

    override fun editToDo(id: Int, title: String, description: String) {
        toDoDao.editToDo(id, title, description)
    }

}