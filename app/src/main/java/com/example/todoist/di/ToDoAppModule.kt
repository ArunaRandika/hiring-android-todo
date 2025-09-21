package com.example.todoist.di

import android.content.Context
import androidx.room.Room
import com.example.todoist.data.repository.ToDoDataRepositoryImpl
import com.example.todoist.data.repository.ToDoRepositoryImpl
import com.example.todoist.data.storage.ToDoDataBase
import com.example.todoist.data.storage.dao.TodoDao
import com.example.todoist.domain.repository.ToDoDataRepository
import com.example.todoist.domain.repository.ToDoRepository
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

    @Provides
    @Singleton
    fun provideToDoDataRepository(todoDao: TodoDao): ToDoDataRepository {
        return ToDoDataRepositoryImpl(todoDao)
    }

    @Provides
    @Singleton
    fun provideTodoRepository(
        toDoDataRepository: ToDoDataRepository
    ): ToDoRepository {
        return ToDoRepositoryImpl(toDoDataRepository)
    }
}