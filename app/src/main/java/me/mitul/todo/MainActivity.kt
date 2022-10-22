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

package me.mitul.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.mitul.todo.data.*
import me.mitul.todo.ui.todo.TodoScreen

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                LaunchedEffect(Unit) {
                    sampleTodos.forEach {
                        viewModel.addItem(TodoItem(it, generateRandomIcon()))
                    }
                }
                TodoScreen(
                    items = viewModel.todoItems,
                    currentlyEditing = viewModel.currentEditItem,
                    onAddItem = viewModel::addItem,
                    onRemoveItem = viewModel::removeItem,
                    onStartEdit = viewModel::onEditItemSelected,
                    onEditItemChange = viewModel::onEditItemChange,
                    onEditDone = viewModel::onEditDone
                )
            }
        }
    }
}

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (darkTheme) darkColors(Color(0xFF3700B3)) else lightColors(Color(0xFF6200EE)),
        shapes = Shapes(
            small = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(4.dp),
            large = RoundedCornerShape(0.dp),
        ),
        typography = Typography(
            body1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            button = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp
            ),
            caption = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        ),
        content = content
    )
}
