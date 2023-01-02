package me.mitul.todo.screens.edit_task

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import me.mitul.todo.common.TASK_DEFAULT_ID
import me.mitul.todo.common.extension.idFromParameter
import me.mitul.todo.model.Task
import me.mitul.todo.model.service.LogService
import me.mitul.todo.model.service.StorageService
import me.mitul.todo.screens.TodoViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    logService: LogService,
    private val storageService: StorageService,
) : TodoViewModel(logService) {
    val task = mutableStateOf(value = Task())

    fun initialize(taskId: String) = launchCatching {
        if (taskId != TASK_DEFAULT_ID) {
            task.value = storageService.getTask(taskId = taskId.idFromParameter()) ?: Task()
        }
    }

    fun onTitleChange(newValue: String) {
        task.value = task.value.copy(title = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        task.value = task.value.copy(description = newValue)
    }

    fun onUrlChange(newValue: String) {
        task.value = task.value.copy(url = newValue)
    }

    fun onDateChange(newValue: Long) {
        val calendar = Calendar.getInstance(/* zone = */ TimeZone.getTimeZone(UTC))
        calendar.timeInMillis = newValue
        val newDueDate =
            SimpleDateFormat(/* pattern = */ DATE_FORMAT, /* locale = */ Locale.ENGLISH)
                .format(/* date = */ calendar.time)
        task.value = task.value.copy(dueDate = newDueDate)
    }

    fun onTimeChange(hour: Int, minute: Int) {
        val newDueTime = "${hour.toClockPattern()}:${minute.toClockPattern()}"
        task.value = task.value.copy(dueTime = newDueTime)
    }

    fun onFlagToggle(newValue: String) {
        val newFlagOption = EditFlagOption.getBooleanValue(flagOption = newValue)
        task.value = task.value.copy(flag = newFlagOption)
    }

    fun onPriorityChange(newValue: String) {
        task.value = task.value.copy(priority = newValue)
    }

    fun onDoneClick(pop: () -> Unit) = launchCatching {
        val editedTask = task.value
        if (editedTask.id.isBlank()) {
            storageService.save(task = editedTask)
        } else {
            storageService.update(task = editedTask)
        }
        pop()
    }

    private fun Int.toClockPattern(): String = if (this < 10) "0$this" else "$this"

    companion object {
        private const val UTC = "UTC"
        private const val DATE_FORMAT = "EEE, d MMM yyyy"
    }
}
