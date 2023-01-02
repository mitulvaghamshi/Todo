package me.mitul.todo.app

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import me.mitul.todo.common.SPLASH_SCREEN
import me.mitul.todo.common.snackbar.SnackBarManager

@Composable
@ExperimentalMaterialApi
@ExperimentalLifecycleComposeApi
fun MainContent() = AppTheme {
    Surface(color = MaterialTheme.colors.background) {
        val appState = rememberAppState()

        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier.padding(8.dp),
                    snackbar = { snackBarData ->
                        Snackbar(
                            snackbarData = snackBarData,
                            contentColor = MaterialTheme.colors.onPrimary,
                        )
                    }
                )
            },
            scaffoldState = appState.scaffoldState
        ) { innerPaddingModifier ->
            NavHost(
                navController = appState.navController,
                startDestination = SPLASH_SCREEN,
                modifier = Modifier.padding(innerPaddingModifier)
            ) {
                navGraph(appState)
            }
        }
    }
}

@Composable
private fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackBarManager: SnackBarManager = SnackBarManager,
    resources: Resources = LocalContext.current.resources,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember(
    scaffoldState,
    navController,
    snackBarManager,
    resources,
    coroutineScope,
) {
    AppState(
        scaffoldState = scaffoldState,
        navController = navController,
        snackBarManager = snackBarManager,
        resources = resources,
        coroutineScope = coroutineScope,
    )
}
