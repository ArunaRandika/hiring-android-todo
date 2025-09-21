package com.example.todoist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_item")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
)