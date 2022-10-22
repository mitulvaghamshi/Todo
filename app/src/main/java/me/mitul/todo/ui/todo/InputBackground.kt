/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.mitul.todo.ui.todo

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
