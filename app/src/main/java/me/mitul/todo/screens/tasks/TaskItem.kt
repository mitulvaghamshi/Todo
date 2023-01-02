package me.mitul.todo.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.mitul.todo.app.DarkOrange
import me.mitul.todo.common.composable.DropdownContextMenu
import me.mitul.todo.common.extension.contextMenu
import me.mitul.todo.common.extension.hasDueDate
import me.mitul.todo.common.extension.hasDueTime
import me.mitul.todo.model.Task
import me.mitul.todo.R.drawable as AppIcon

@Composable
@ExperimentalMaterialApi
fun TaskItem(
    task: Task,
    options: List<String>,
    onCheckChange: () -> Unit,
    onActionClick: (String) -> Unit,
) = Card(
    backgroundColor = MaterialTheme.colors.background,
    modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Checkbox(
            checked = task.completed,
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 0.dp,
            ),
            onCheckedChange = { onCheckChange() },
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.subtitle2,
            )

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = getDueDateAndTime(task),
                    fontSize = 12.sp,
                )
            }
        }

        if (task.flag) {
            Icon(
                painter = painterResource(AppIcon.ic_flag),
                contentDescription = "Flag",
                tint = DarkOrange,
            )
        }

        DropdownContextMenu(
            options = options,
            modifier = Modifier.contextMenu(),
            onActionClick = onActionClick,
        )
    }
}

private fun getDueDateAndTime(task: Task): String {
    val builder = StringBuilder()
    if (task.hasDueDate()) builder.append(task.dueDate).append(" ")
    if (task.hasDueTime()) builder.append("at ").append(task.dueTime)
    return builder.toString()
}

@Preview(showBackground = true)
@Composable
@ExperimentalMaterialApi
private fun TaskItem_Preview() {
    val task = Task(
        id = "1",
        title = "Buy Apples",
        priority = "Low",
        dueDate = "01/01/2023",
        dueTime = "12:35 AM",
        description = "Today's shopping list item...",
        completed = false,
        flag = true,
        url = "https://www.google.com/search?q=apples",
    )

    val options = listOf(
        "Edit item",
        "Delete item",
        "Toggle flag",
    )

    TaskItem(
        task = task,
        options = options,
        onCheckChange = { }
    ) {}
}
