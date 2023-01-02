package me.mitul.todo.model.service

import kotlinx.coroutines.flow.Flow
import me.mitul.todo.model.Task

interface StorageService {
    val tasks: Flow<List<Task>>
    suspend fun getTask(taskId: String): Task?
    suspend fun save(task: Task): String
    suspend fun update(task: Task)
    suspend fun delete(taskId: String)
    suspend fun deleteAllForUser(userId: String)
}
