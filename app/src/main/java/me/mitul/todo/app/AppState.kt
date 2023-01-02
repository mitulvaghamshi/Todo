package me.mitul.todo.app

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import me.mitul.todo.common.snackbar.SnackBarManager
import me.mitul.todo.common.snackbar.SnackBarMessage.Companion.toMessage

@Stable
class AppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val snackBarManager: SnackBarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope,
) {
    init {
        coroutineScope.launch {
            snackBarManager.snackBarMessages.filterNotNull().collect { snackBarMessage ->
                val text = snackBarMessage.toMessage(resources = resources)
                scaffoldState.snackbarHostState.showSnackbar(message = text)
            }
        }
    }

    fun pop() = navController.popBackStack()

    fun push(route: String) = navController.navigate(route = route) {
        launchSingleTop = true
    }

    fun navigate(route: String, popUp: String) = navController.navigate(route) {
        launchSingleTop = true
        popUpTo(popUp) {
            inclusive = true
        }
    }

    fun navigate(route: String) = navController.navigate(route) {
        launchSingleTop = true
        popUpTo(0) {
            inclusive = true
        }
    }
}
