package me.mitul.todo.todo

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.todo.data.TodoIcon
import me.mitul.todo.todo.data.TodoItem

@Composable
fun ItemEntryInput(onItemComplete: (TodoItem) -> Unit) {
    val (text, setText) = remember { mutableStateOf("") }
    val (icon, setIcon) = remember { mutableStateOf(TodoIcon.Default) }
    val iconsVisible = text.isNotBlank()
    val submit = {
        onItemComplete(TodoItem(text, icon))
        setIcon(TodoIcon.Default)
        setText("")
    }
    ItemInput(
        text = text,
        onTextChange = setText,
        icon = icon,
        onIconChange = setIcon,
        submit = submit,
        iconsVisible = iconsVisible,
        buttonSlot = {
            EditButton(
                text = "Add",
                onClick = submit,
                enabled = text.isNotBlank(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewItemEntryInput() {
    ItemEntryInput {}
}
