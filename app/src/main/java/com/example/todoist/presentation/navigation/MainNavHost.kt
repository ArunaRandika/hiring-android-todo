package com.example.todoist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.todoist.presentation.intent.ToDoDetailsIntent
import com.example.todoist.presentation.intent.TodoMainScreenIntent
import com.example.todoist.presentation.screen.edit_screen.EditScreen
import com.example.todoist.presentation.screen.main_screen.MainScreen
import com.example.todoist.presentation.screen.splash_screen.SplashScreen
import com.example.todoist.presentation.viewmodel.AddEditViewModel
import com.example.todoist.presentation.viewmodel.TodoMainViewModel

import kotlinx.coroutines.delay

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    var loadingCompleted by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(10)
        loadingCompleted = true
    }
    NavHost(
        navController = navController,
        startDestination = SplashScreenNav

    ) {
        composable<SplashScreenNav> {
            SplashScreen(
                isLoading = loadingCompleted,
                onLoadingCompleted = {
                    navController.navigate(ToDoMainScreenNav)
                }
            )
        }

        navigation<AuthRoute>(startDestination = LoginRoute) {
            composable<LoginRoute> {

            }

        }

        composable<ToDoMainScreenNav> {
            val mainListScreenViewModel = hiltViewModel<TodoMainViewModel>()
            val todoState by mainListScreenViewModel.toDoItemState.collectAsStateWithLifecycle()
            MainScreen (
                todoState = todoState,
                onEvent = {

                    when (it) {

                        is TodoMainScreenIntent.Delete -> {
                            mainListScreenViewModel.deleteTodo(it.toDoId)
                        }

                        is TodoMainScreenIntent.FetchAll -> {
                            mainListScreenViewModel.fetchTodoItems()
                        }

                        is TodoMainScreenIntent.MarkAsCompleted -> {
                            mainListScreenViewModel.markAsCompleted(
                                todoId = it.toDoId,
                                checked = it.isChecked
                            )
                        }

                        is TodoMainScreenIntent.EditTodoMainScreen -> {
                            navController.navigate(
                                AddEditToDoNav(
                                    id = it.toDoId,
                                    title = it.title,
                                    description = it.description

                                )
                            )
                        }

                        is TodoMainScreenIntent.AddTodo -> {
                            navController.navigate(AddEditToDoNav())
                        }


                    }


                },
            )

        }

        composable<AddEditToDoNav> { backStackEntry ->
            val editScreenViewModel = hiltViewModel<AddEditViewModel>()
            val args: AddEditToDoNav = backStackEntry.toRoute()
            val addEditToDoState by editScreenViewModel.addTodoState.collectAsStateWithLifecycle()
            EditScreen(
                isFromEdit = args.id != null,
                addEditToDoNavArg = args,
                addEditToDoState = addEditToDoState,
                onEvent = { event ->
                    when (event) {
                        is ToDoDetailsIntent.AddToDo -> {
                            editScreenViewModel.addTodo(event.title, event.description)
                        }

                        is ToDoDetailsIntent.EditToDo -> {
                            args.id?.let {
                                editScreenViewModel.updateTodo(
                                    args.id,
                                    event.title,
                                    event.description
                                )
                            }
                        }

                        ToDoDetailsIntent.NavigateBack -> {
                            navController.popBackStack()
                        }
                    }

                }
            )
        }


    }
}