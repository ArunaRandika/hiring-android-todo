package com.example.todoist.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoist.data.model.TodoEntity
import com.example.todoist.data.storage.dao.TodoDao

@Database(entities = [TodoEntity::class], version = 2)
abstract class ToDoDataBase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}