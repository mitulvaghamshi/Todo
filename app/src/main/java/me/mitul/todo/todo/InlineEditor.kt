package me.mitul.todo.todo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.todo.data.TodoItem
import me.mitul.todo.todo.data.randomTodoItem

@Composable
fun InlineEditor(
    item: TodoItem,
    onEditItemChange: (TodoItem) -> Unit,
    onEditDone: () -> Unit,
    onRemoveItem: () -> Unit,
) = ItemInput(
    text = item.task,
    onTextChange = { onEditItemChange(item.copy(task = it)) },
    icon = item.icon,
    onIconChange = { onEditItemChange(item.copy(icon = it)) },
    submit = onEditDone,
    iconsVisible = true
) {
    Row {
        val shrinkButtons = Modifier.widthIn(20.dp)
        TextButton(onEditDone, shrinkButtons) {
            Text("\uD83D\uDCBE", textAlign = TextAlign.End)
        }
        TextButton(onRemoveItem, shrinkButtons) {
            Text("❌", textAlign = TextAlign.End)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewInlineEditor() {
    val item = remember { randomTodoItem() }
    InlineEditor(item, {}, {}, {})
}
