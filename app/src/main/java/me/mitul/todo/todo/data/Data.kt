package me.mitul.todo.todo.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import me.mitul.todo.R
import java.util.*

import kotlin.random.Random

data class TodoItem(
    val task: String,
    val icon: TodoIcon = TodoIcon.Default,
    // since the user may generate identical tasks, give them each a unique ID
    val id: UUID = UUID.randomUUID(),
)

enum class TodoIcon(val imageVector: ImageVector, @StringRes val contentDescription: Int) {
    Square(Icons.Default.CropSquare, R.string.cd_expand),
    Done(Icons.Default.Done, R.string.cd_done),
    Event(Icons.Default.Event, R.string.cd_event),
    Privacy(Icons.Default.PrivacyTip, R.string.cd_privacy),
    Trash(Icons.Default.RestoreFromTrash, R.string.cd_restore);

    companion object {
        val Default = Square
    }
}

fun randomTint() = Random.nextFloat().coerceIn(0.3f, 0.9f)

fun randomIcon() = TodoIcon.values().random()

fun randomTodoItem() = TodoItem(sampleTodos.random(), randomIcon())

val sampleTodos = listOf(
    "Learn compose",
    "Learn state",
    "Build dynamic UIs",
    "Learn Unidirectional Data Flow",
    "Integrate LiveData",
    "Integrate ViewModel",
    "Remember to savedState!",
    "Build stateless composables",
    "Use state from stateless composables",
)
