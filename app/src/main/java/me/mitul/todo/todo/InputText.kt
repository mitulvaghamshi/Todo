package me.mitul.todo.todo

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

/**
 * Styled [TextField] for inputting a [ListItem].
 *
 * @param text (state) current text to display
 * @param onTextChange (event) request the text change state
 * @param modifier the modifier for this element
 * @param onImeAction (event) notify caller of [ImeAction.Done] events
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputText(
    text: String,
    modifier: Modifier = Modifier,
    onImeAction: () -> Unit = {},
    onTextChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        maxLines = 1,
        modifier = modifier,
        onValueChange = onTextChange,
        placeholder = { Text("Add new item...") },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions {
            onImeAction()
            keyboardController?.hide()
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewInputText() {
    InputText("Hello World!") {}
}
