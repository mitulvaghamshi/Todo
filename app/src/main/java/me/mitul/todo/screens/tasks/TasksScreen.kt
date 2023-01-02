package me.mitul.todo.screens.tasks

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.mitul.todo.common.composable.ActionToolbar
import me.mitul.todo.common.extension.smallSpacer
import me.mitul.todo.common.extension.toolbarActions
import me.mitul.todo.R.drawable as AppIcon
import me.mitul.todo.R.string as AppText

@Composable
@ExperimentalMaterialApi
@ExperimentalLifecycleComposeApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun TasksScreen(
    push: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = hiltViewModel(),
) = Scaffold(
    floatingActionButton = {
        FloatingActionButton(
            onClick = { viewModel.onAddClick(push) },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            modifier = modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add",
            )
        }
    },
) {
    val tasks = viewModel.tasks.collectAsStateWithLifecycle(emptyList())
    val options by viewModel.options

    Column(modifier = Modifier.fillMaxSize()) {
        ActionToolbar(
            title = AppText.tasks,
            endActionIcon = AppIcon.ic_settings,
            modifier = Modifier.toolbarActions(),
        ) {
            viewModel.onSettingsClick(push)
        }

        Spacer(modifier = Modifier.smallSpacer())

        LazyColumn {
            items(items = tasks.value, key = { it.id }) { taskItem ->
                TaskItem(
                    task = taskItem,
                    options = options,
                    onCheckChange = {
                        viewModel.onTaskCheckChange(taskItem)
                    },
                ) { action ->
                    viewModel.onTaskActionClick(push, taskItem, action)
                }
            }
        }
    }

    LaunchedEffect(key1 = viewModel) {
        viewModel.loadTaskOptions()
    }
}
