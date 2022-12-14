package me.mitul.todo.todo

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Draw a background based on [MaterialTheme.colors.onSurface] that animates resizing and elevation
 * changes.
 *
 * @param elevate draw a shadow, changes to this will be animated
 * @param modifier modifier for this element
 * @param content (slot) content to draw in the background
 */
@Composable
fun InputBackground(
    elevate: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    val animatedElevation by animateDpAsState(
        if (elevate) 16.dp else 0.dp, TweenSpec(500)
    )
    Surface(
        shape = RectangleShape,
        elevation = animatedElevation,
        content = { Row(modifier.animateContentSize(TweenSpec(300))) { content() } }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewInputBackground() = Column(Modifier.padding(5.dp)) {
    InputBackground(true) { Text("With Elevation") }
    Spacer(Modifier.height(5.dp))
    InputBackground(false) { Text("No Elevation") }
}
