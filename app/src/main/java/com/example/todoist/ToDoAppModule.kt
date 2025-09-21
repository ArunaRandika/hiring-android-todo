package com.example.todoist

import android.content.Context
import androidx.room.Room
import com.example.todoist.data.storage.ToDoDataBase
import com.example.todoist.data.storage.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context): ToDoDataBase {
        return Room.databaseBuilder(
            context,
            ToDoDataBase::class.java,
            "todo_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(database: ToDoDataBase): TodoDao {
        return database.todoDao()
    }
}