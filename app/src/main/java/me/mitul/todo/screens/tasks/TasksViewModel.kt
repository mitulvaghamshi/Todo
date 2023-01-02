package me.mitul.todo.screens.tasks

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import me.mitul.todo.common.EDIT_TASK_SCREEN
import me.mitul.todo.common.SETTINGS_SCREEN
import me.mitul.todo.common.TASK_ID
import me.mitul.todo.model.Task
import me.mitul.todo.model.service.ConfigurationService
import me.mitul.todo.model.service.LogService
import me.mitul.todo.model.service.StorageService
import me.mitul.todo.screens.TodoViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    logService: LogService,
    private val storageService: StorageService,
    private val configurationService: ConfigurationService,
) : TodoViewModel(logService) {
    val tasks = storageService.tasks
    val options = mutableStateOf<List<String>>(listOf())

    fun loadTaskOptions() {
        val hasEditOption = configurationService.isShowTaskEditButtonConfig
        options.value = TaskActionOption.getOptions(hasEditOption = hasEditOption)
    }

    fun onTaskCheckChange(task: Task) = launchCatching {
        storageService.update(task.copy(completed = !task.completed))
    }

    fun onAddClick(push: (String) -> Unit) = push(EDIT_TASK_SCREEN)

    fun onSettingsClick(push: (String) -> Unit) = push(SETTINGS_SCREEN)

    fun onTaskActionClick(push: (String) -> Unit, task: Task, action: String) {
        when (TaskActionOption.getByTitle(title = action)) {
            TaskActionOption.EditTask -> push("$EDIT_TASK_SCREEN?$TASK_ID={${task.id}}")
            TaskActionOption.ToggleFlag -> onFlagTaskClick(task = task)
            TaskActionOption.DeleteTask -> onDeleteTaskClick(task = task)
        }
    }

    private fun onFlagTaskClick(task: Task) = launchCatching {
        storageService.update(task = task.copy(flag = !task.flag))
    }

    private fun onDeleteTaskClick(task: Task) = launchCatching {
        storageService.delete(taskId = task.id)
    }
}
