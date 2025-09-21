package com.example.todoist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoist.domain.usecase.CompleteToDoUseCase
import com.example.todoist.domain.usecase.DeleteToDoUseCase
import com.example.todoist.domain.usecase.GetTodoItemsUseCase
import com.example.todoist.presentation.state.TodoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoMainViewModel @Inject constructor(
    private val getTodoItemsUseCase: GetTodoItemsUseCase,
    private val deleteToDoUseCase: DeleteToDoUseCase,
    private val markAsCompleted: CompleteToDoUseCase,
) : ViewModel() {

    private val _toDoItemState = MutableStateFlow<TodoState>(TodoState.Loading)
    val toDoItemState = _toDoItemState.asStateFlow()


    fun fetchTodoItems() {
        viewModelScope.launch(Dispatchers.IO) {

            getTodoItemsUseCase.invoke().collect { result ->
                result.fold(
                    onSuccess = { items ->
                        if (!items.isNullOrEmpty()) {
                            _toDoItemState.update {
                                TodoState.Success(items)
                            }
                        } else {
                            _toDoItemState.update {
                                TodoState.Empty
                            }
                        }

                    },
                    onFailure = { throwable ->
                        _toDoItemState.update {
                            TodoState.Error("Something went wrong")
                        }
                    }
                )

            }

        }

    }

    fun markAsCompleted(todoId: Int, checked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            markAsCompleted.invoke(id = todoId, checked = checked).collect { result ->
                result.fold(
                    onSuccess = {
                        fetchTodoItems()
                    },
                    onFailure = {
                        _toDoItemState.update {
                            TodoState.Error("Something went wrong")
                        }
                    })
            }

        }
    }

    fun deleteTodo(todoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteToDoUseCase.invoke(id = todoId).collect { result ->
                result.fold(
                    onSuccess = {
                        fetchTodoItems()
                    },
                    onFailure = {
                        _toDoItemState.update {
                            TodoState.Error("Something went wrong")
                        }
                    })
            }

        }
    }


}