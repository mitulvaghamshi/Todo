package me.mitul.todo.todo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.todo.data.TodoIcon

/**
 * Draws a row of [TodoIcon] with visibility changes animated.
 *
 * When not visible, will collapse to 16.dp high by default. You can enlarge this with the passed
 * modifier.
 *
 * @param icon (state) the current selected icon
 * @param onIconChange (event) request the selected icon change
 * @param modifier modifier for this element
 * @param visible (state) if the icon should be shown
 */
@Composable
fun AnimatedIconRow(
    icon: TodoIcon,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    onIconChange: (TodoIcon) -> Unit,
) {
    // remember these specs so they don't restart if recomposing during the animation
    // this is required since TweenSpec restarts on interruption
    val enter = remember { fadeIn(TweenSpec(300, easing = FastOutLinearInEasing)) }
    val exit = remember { fadeOut(TweenSpec(100, easing = FastOutSlowInEasing)) }
    Box(modifier.defaultMinSize(minHeight = 16.dp)) {
        AnimatedVisibility(visible, enter = enter, exit = exit) {
            IconRow(icon, onIconChange = onIconChange)
        }
    }
}

/**
 * Displays a row of selectable [TodoIcon]
 *
 * @param icon (state) the current selected icon
 * @param onIconChange (event) request the selected icon change
 * @param modifier modifier for this element
 */
@Composable
private fun IconRow(
    icon: TodoIcon,
    modifier: Modifier = Modifier,
    onIconChange: (TodoIcon) -> Unit,
) = Row(modifier) {
    for (todoIcon in TodoIcon.values()) {
        SelectableIconButton(
            icon = todoIcon.imageVector,
            contentDescription = todoIcon.contentDescription,
            onIconSelected = { onIconChange(todoIcon) },
            isSelected = todoIcon == icon
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewIconRow() {
    IconRow(TodoIcon.Default) {}
}
