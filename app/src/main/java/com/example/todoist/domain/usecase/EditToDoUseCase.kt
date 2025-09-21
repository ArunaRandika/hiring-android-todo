package com.example.todoist.domain.usecase

import com.example.todoist.domain.repository.ToDoRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EditToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
) {

    suspend operator fun invoke(id: Int, title: String, description: String): Flow<Result<Unit>> =
        repository.editItem(id, title, description).map { result ->
            result.fold(
                onSuccess = { Result.success(Unit) },
                onFailure = { error -> Result.failure(error) }
            )
        }
}