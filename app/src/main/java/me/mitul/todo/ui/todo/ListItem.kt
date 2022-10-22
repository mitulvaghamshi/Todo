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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.data.TodoItem
import me.mitul.todo.data.generateRandomTodoItem
import me.mitul.todo.data.randomTint

/**
 * Stateless composable that displays a full-width [TodoItem].
 *
 * @param todo item to show
 * @param onItemClicked (event) notify caller that the row was clicked
 * @param modifier modifier for this element
 */
@Composable
fun ListItem(
    todo: TodoItem,
    modifier: Modifier = Modifier,
    iconAlpha: Float = remember(todo.id) { randomTint() },
    onItemClicked: (TodoItem) -> Unit
) = Row(modifier
    .clickable { onItemClicked(todo) }
    .padding(horizontal = 16.dp, vertical = 8.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    content = {
        Text(todo.task)
        Icon(
            imageVector = todo.icon.imageVector,
            tint = LocalContentColor.current.copy(iconAlpha),
            contentDescription = stringResource(todo.icon.contentDescription)
        )
    }
)

@Preview(showBackground = true)
@Composable
private fun PreviewTodoRow() {
    val todo = remember { generateRandomTodoItem() }
    ListItem(todo, Modifier.fillMaxWidth()) {}
}
