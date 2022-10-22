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

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.data.TodoIcon

@Composable
fun ItemInput(
    text: String,
    onTextChange: (String) -> Unit,
    icon: TodoIcon,
    onIconChange: (TodoIcon) -> Unit,
    submit: () -> Unit,
    iconsVisible: Boolean,
    buttonSlot: @Composable () -> Unit
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
