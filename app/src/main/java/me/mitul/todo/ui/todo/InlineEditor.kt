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
import me.mitul.todo.data.TodoItem
import me.mitul.todo.data.generateRandomTodoItem

@Composable
fun InlineEditor(
    item: TodoItem,
    onEditItemChange: (TodoItem) -> Unit,
    onEditDone: () -> Unit,
    onRemoveItem: () -> Unit
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
    val item = remember { generateRandomTodoItem() }
    InlineEditor(item, {}, {}, {})
}
