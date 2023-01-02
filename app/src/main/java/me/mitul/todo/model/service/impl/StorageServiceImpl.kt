package me.mitul.todo.model.service.impl

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.tasks.await
import me.mitul.todo.model.Task
import me.mitul.todo.model.service.AccountService
import me.mitul.todo.model.service.StorageService
import me.mitul.todo.model.service.trace
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val auth: AccountService,
) : StorageService {
    @OptIn(ExperimentalCoroutinesApi::class)
    override val tasks: Flow<List<Task>>
        get() = auth.currentUser.flatMapLatest { user ->
            currentCollection(uid = user.id).snapshots().map { snapshot ->
                snapshot.toObjects()
            }
        }

    override suspend fun getTask(taskId: String): Task? =
        currentCollection(uid = auth.currentUserId)
            .document(/* documentPath = */ taskId)
            .get()
            .await()
            .toObject()

    override suspend fun save(task: Task): String = trace(SAVE_TASK_TRACE) {
        currentCollection(uid = auth.currentUserId)
            .add(/* data = */ task)
            .await()
            .id
    }

    override suspend fun update(task: Task): Unit = trace(UPDATE_TASK_TRACE) {
        currentCollection(uid = auth.currentUserId)
            .document(/* documentPath = */ task.id)
            .set(/* data = */ task)
            .await()
    }

    override suspend fun delete(taskId: String) {
        currentCollection(uid = auth.currentUserId)
            .document(/* documentPath = */ taskId)
            .delete()
            .await()
    }

    // TODO: It's not recommended to delete on the client:
    // https://firebase.google.com/docs/firestore/manage-data/delete-data#kotlin+ktx_2
    override suspend fun deleteAllForUser(userId: String) {
        val matchingTasks = currentCollection(uid = userId)
            .get()
            .await()

        matchingTasks.map {
            it.reference.delete().asDeferred()
        }.awaitAll()
    }

    private fun currentCollection(uid: String): CollectionReference =
        fireStore.collection(/* collectionPath = */ USER_COLLECTION)
            .document(/* documentPath = */ uid)
            .collection(/* collectionPath = */ TASK_COLLECTION)

    companion object {
        private const val USER_COLLECTION = "users"
        private const val TASK_COLLECTION = "tasks"
        private const val SAVE_TASK_TRACE = "saveTask"
        private const val UPDATE_TASK_TRACE = "updateTask"
    }
}
