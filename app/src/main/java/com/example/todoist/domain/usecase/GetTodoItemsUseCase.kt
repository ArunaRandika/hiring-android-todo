package com.example.todoist.domain.usecase

import com.example.todoist.domain.model.ToDoItem
import com.example.todoist.domain.repository.ToDoRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTodoItemsUseCase @Inject constructor(
    private val repository: ToDoRepository
) {

    suspend operator fun invoke(): Flow<Result<List<ToDoItem>>> =
        repository.getTodos().map { item ->
            item.fold(
                onSuccess = { Result.success(it) },
                onFailure = { Result.failure(it) }
            )

        }
}