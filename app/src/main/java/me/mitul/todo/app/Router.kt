package me.mitul.todo.app

import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.mitul.todo.common.*
import me.mitul.todo.screens.edit_task.EditTaskScreen
import me.mitul.todo.screens.login.LoginScreen
import me.mitul.todo.screens.settings.SettingsScreen
import me.mitul.todo.screens.sign_up.SignUpScreen
import me.mitul.todo.screens.splash.SplashScreen
import me.mitul.todo.screens.tasks.TasksScreen

@ExperimentalMaterialApi
@ExperimentalLifecycleComposeApi
fun NavGraphBuilder.navGraph(appState: AppState) {
    composable(SPLASH_SCREEN) {
        SplashScreen(
            popUpAndPush = { route, popUp ->
                appState.navigate(route, popUp)
            },
        )
    }

    composable(LOGIN_SCREEN) {
        LoginScreen(
            openAndPopUp = { route, popUp ->
                appState.navigate(route, popUp)
            }
        )
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(
            popUpAndPush = { route, popUp ->
                appState.navigate(route, popUp)
            }
        )
    }

    composable(TASKS_SCREEN) {
        TasksScreen(
            push = { route ->
                appState.push(route)
            }
        )
    }

    composable(SETTINGS_SCREEN) {
        SettingsScreen(
            restartApp = { route ->
                appState.navigate(route)
            },
            push = { route ->
                appState.push(route)
            }
        )
    }

    composable(
        route = "$EDIT_TASK_SCREEN$TASK_ID_ARG",
        arguments = listOf(
            navArgument(TASK_ID) {
                defaultValue = TASK_DEFAULT_ID
            }
        )
    ) {
        EditTaskScreen(
            pop = {
                appState.pop()
            },
            taskId = it.arguments?.getString(TASK_ID) ?: TASK_DEFAULT_ID
        )
    }
}
