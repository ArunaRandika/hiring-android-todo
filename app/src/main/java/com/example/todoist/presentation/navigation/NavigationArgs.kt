package com.example.todoist.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object SplashScreenNav

@Serializable
object AuthRoute

@Serializable
object LoginRoute

@Serializable
object ToDoMainScreenNav

@Serializable
data class AddEditToDoNav(
    val id: Int? = null,
    val title: String? = "",
    val description: String? = ""
)