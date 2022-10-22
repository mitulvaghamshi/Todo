package me.mitul.todo.ui.todo

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Styled button for [TodoScreen]
 *
 * @param onClick (event) notify caller of click events
 * @param text button text
 * @param modifier modifier for button
 * @param enabled enable or disable the button
 */
@Composable
fun EditButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
    onClick: () -> Unit,
) = TextButton(
    colors = colors,
    onClick = onClick,
    enabled = enabled,
    modifier = modifier,
    shape = CircleShape,
    content = { Text(text) }
)

@Preview(showBackground = true)
@Composable
private fun PreviewEditButton() {
    EditButton("Add") {}
}
