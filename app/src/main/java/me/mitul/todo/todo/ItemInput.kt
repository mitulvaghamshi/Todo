package me.mitul.todo.todo

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.todo.data.TodoIcon

@Composable
fun ItemInput(
    text: String,
    onTextChange: (String) -> Unit,
    icon: TodoIcon,
    onIconChange: (TodoIcon) -> Unit,
    submit: () -> Unit,
    iconsVisible: Boolean,
    buttonSlot: @Composable () -> Unit,
) = InputBackground(elevate = true) {
    Column {
        Row {
            InputText(
                text = text,
                onImeAction = submit,
                onTextChange = onTextChange,
                modifier = Modifier.weight(1f)
            )
            Box(Modifier.align(Alignment.CenterVertically)) { buttonSlot() }
        }
        if (iconsVisible) {
            AnimatedIconRow(icon, onIconChange = onIconChange)
        } else {
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewItemInput() {
    ItemInput("Android", {}, TodoIcon.Default, {}, {}, true, {})
}

@Preview(showBackground = true)
@Composable
private fun PreviewItemInput2() {
    ItemInput("Android", {}, TodoIcon.Default, {}, {}, false, {})
}
