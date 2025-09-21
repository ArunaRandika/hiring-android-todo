package com.example.todoist.presentation.state

sealed class AddEditState {
    data object Loading : AddEditState()
    data object Success : AddEditState()
    data object Empty : AddEditState()
    data class Error(val errorMessage: String) : AddEditState()
}