package com.example.todoist.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoist.data.model.TodoEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveToDo(todo: TodoEntity)

    @Query("SELECT * FROM todo_item")
    fun getAllToDoItems(): Flow<List<TodoEntity>>

    @Query("UPDATE todo_item SET isCompleted = :checked WHERE id = :id")
    fun markAsCompleted(id: Int, checked: Boolean)

    @Query("DELETE FROM todo_item WHERE id = :id")
    fun deleteToDo(id: Int)

    @Query("UPDATE todo_item SET title = :title, description = :description WHERE id = :id")
    fun editToDo(id: Int, title: String, description: String)

    @Query("SELECT * FROM todo_item WHERE id = :id")
    fun getToDoById(id: Int): TodoEntity?
}