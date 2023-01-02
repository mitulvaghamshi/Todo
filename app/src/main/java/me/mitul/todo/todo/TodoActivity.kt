package me.mitul.todo.todo

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
import me.mitul.todo.todo.data.TodoItem
import me.mitul.todo.todo.data.TodoViewModel
import me.mitul.todo.todo.data.randomIcon
import me.mitul.todo.todo.data.sampleTodos

class TodoActivity : ComponentActivity() {
    private val viewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                LaunchedEffect(Unit) {
                    sampleTodos.forEach {
                        viewModel.addItem(TodoItem(it, randomIcon()))
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
private fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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
