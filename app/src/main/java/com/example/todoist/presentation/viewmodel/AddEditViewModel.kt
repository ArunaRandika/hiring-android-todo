package com.example.todoist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoist.domain.usecase.AddToDoUseCase
import com.example.todoist.domain.usecase.EditToDoUseCase
import com.example.todoist.presentation.state.AddEditState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val addToDoUseCase: AddToDoUseCase,
    private val editToDoUseCase: EditToDoUseCase,
) : ViewModel() {

    private val _addTodoState = MutableStateFlow<AddEditState>(AddEditState.Empty)
    val addTodoState = _addTodoState.asStateFlow()

    fun addTodo(title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _addTodoState.update {
                AddEditState.Loading
            }
            addToDoUseCase.invoke(title, description).collect { result ->
                result.fold(
                    onSuccess = {
                        _addTodoState.update {
                            AddEditState.Success
                        }
                    },
                    onFailure = { error ->
                        _addTodoState.update {
                            AddEditState.Error(error.message ?: "Error occurred")
                        }
                    }
                )
            }
        }


    }

    fun updateTodo(id: Int, title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _addTodoState.update {
                AddEditState.Loading
            }
            editToDoUseCase.invoke(id, title, description).collect { result ->
                result.fold(
                    onSuccess = {
                        _addTodoState.update {
                            AddEditState.Success
                        }
                    },
                    onFailure = { error ->
                        _addTodoState.update {
                            AddEditState.Error(error.message ?: "Error occurred")
                        }
                    }
                )
            }
        }
    }


}