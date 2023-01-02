package me.mitul.todo.todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.todo.data.TodoIcon
import me.mitul.todo.todo.data.TodoItem

/**
 * Stateless component that is responsible for the entire todo screen.
 *
 * @param items (state) list of [TodoItem] to display
 * @param onAddItem (event) request an item be added
 * @param onRemoveItem (event) request an item be removed
 */
@Composable
fun TodoScreen(
    items: List<TodoItem>,
    currentlyEditing: TodoItem?,
    onAddItem: (TodoItem) -> Unit,
    onRemoveItem: (TodoItem) -> Unit,
    onStartEdit: (TodoItem) -> Unit,
    onEditItemChange: (TodoItem) -> Unit,
    onEditDone: () -> Unit,
) = Column {
    val enableTopSection = currentlyEditing == null
    InputBackground(enableTopSection) {
        if (enableTopSection) ItemEntryInput(onAddItem) else EditingHeader(onEditDone)
    }
    LazyColumn(Modifier.weight(1f), contentPadding = PaddingValues(top = 8.dp)) {
        items(items) { todo ->
            if (currentlyEditing?.id == todo.id) {
                InlineEditor(currentlyEditing, onEditItemChange, onEditDone) { onRemoveItem(todo) }
            } else {
                ListItem(todo, Modifier.fillParentMaxWidth()) { onStartEdit(it) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTodoScreen() {
    val items = listOf(
        TodoItem("Learn compose", TodoIcon.Event),
        TodoItem("Take the codelab"),
        TodoItem("Apply state", TodoIcon.Done),
        TodoItem("Build dynamic UIs", TodoIcon.Square)
    )
    TodoScreen(items, null, {}, {}, {}, {}, {})
}
