package me.mitul.todo.screens.edit_task

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import me.mitul.todo.common.composable.ActionToolbar
import me.mitul.todo.common.composable.BasicField
import me.mitul.todo.common.composable.CardSelector
import me.mitul.todo.common.composable.RegularCardEditor
import me.mitul.todo.common.extension.card
import me.mitul.todo.common.extension.fieldModifier
import me.mitul.todo.common.extension.spacer
import me.mitul.todo.common.extension.toolbarActions
import me.mitul.todo.model.Priority
import me.mitul.todo.model.Task
import me.mitul.todo.R.drawable as AppIcon
import me.mitul.todo.R.string as AppText

@Composable
@ExperimentalMaterialApi
fun EditTaskScreen(
    pop: () -> Unit,
    taskId: String,
    modifier: Modifier = Modifier,
    viewModel: EditTaskViewModel = hiltViewModel(),
) {
    val task by viewModel.task

    LaunchedEffect(key1 = Unit) {
        viewModel.initialize(taskId = taskId)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionToolbar(
            title = AppText.edit_task,
            endActionIcon = AppIcon.ic_check,
            modifier = Modifier.toolbarActions()
        ) {
            viewModel.onDoneClick(pop)
        }

        Spacer(modifier = Modifier.spacer())

        BasicField(
            text = AppText.title,
            value = task.title,
            modifier = Modifier.fieldModifier(),
            onNewValue = viewModel::onTitleChange
        )

        BasicField(
            text = AppText.description,
            value = task.description,
            modifier = Modifier.fieldModifier(),
            onNewValue = viewModel::onDescriptionChange
        )

        BasicField(
            text = AppText.url,
            value = task.url,
            modifier = Modifier.fieldModifier(),
            onNewValue = viewModel::onUrlChange
        )

        Spacer(modifier = Modifier.spacer())

        CardEditors(
            task = task,
            onDateChange = viewModel::onDateChange,
            onTimeChange = viewModel::onTimeChange
        )

        CardSelectors(
            task = task,
            onPriorityChange = viewModel::onPriorityChange,
            onFlagToggle = viewModel::onFlagToggle
        )

        Spacer(modifier = Modifier.spacer())
    }
}

@ExperimentalMaterialApi
@Composable
private fun CardEditors(
    task: Task,
    onDateChange: (Long) -> Unit,
    onTimeChange: (Int, Int) -> Unit,
) {
    val activity = LocalContext.current as AppCompatActivity

    RegularCardEditor(
        title = AppText.date,
        icon = AppIcon.ic_calendar,
        content = task.dueDate,
        modifier = Modifier.card(),
    ) {
        showDatePicker(
            activity = activity,
            onDateChange = onDateChange,
        )
    }
    RegularCardEditor(
        title = AppText.time,
        icon = AppIcon.ic_clock,
        content = task.dueTime,
        modifier = Modifier.card(),
    ) {
        showTimePicker(
            activity = activity,
            onTimeChange = onTimeChange,
        )
    }
}

@Composable
@ExperimentalMaterialApi
private fun CardSelectors(
    task: Task,
    onPriorityChange: (String) -> Unit,
    onFlagToggle: (String) -> Unit,
) {
    val prioritySelection = Priority.getByName(name = task.priority).name

    CardSelector(
        label = AppText.priority,
        options = Priority.getOptions(),
        selection = prioritySelection,
        modifier = Modifier.card(),
    ) { value ->
        onPriorityChange(value)
    }

    val flagSelection = EditFlagOption
        .getByCheckedState(checkedState = task.flag).name

    CardSelector(
        label = AppText.flag,
        options = EditFlagOption.getOptions(),
        selection = flagSelection,
        modifier = Modifier.card(),
    ) { value ->
        onFlagToggle(value)
    }
}

private fun showDatePicker(
    activity: AppCompatActivity?,
    onDateChange: (Long) -> Unit,
) {
    val picker = MaterialDatePicker
        .Builder
        .datePicker()
        .build()

    activity?.let {
        picker.show(
            /* manager = */
            it.supportFragmentManager,
            /* tag = */
            picker.toString(),
        )

        picker.addOnPositiveButtonClickListener { timeInMillis ->
            onDateChange(timeInMillis)
        }
    }
}

private fun showTimePicker(
    activity: AppCompatActivity?,
    onTimeChange: (Int, Int) -> Unit,
) {
    val picker = MaterialTimePicker
        .Builder()
        .setTimeFormat(/* format = */ TimeFormat.CLOCK_24H)
        .build()

    activity?.let {
        picker.show(
            /* manager = */
            it.supportFragmentManager,
            /* tag = */
            picker.toString(),
        )
        
        picker.addOnPositiveButtonClickListener {
            onTimeChange(
                picker.hour,
                picker.minute,
            )
        }
    }
}
