package me.mitul.todo.model.service.impl

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import me.mitul.todo.model.User
import me.mitul.todo.model.service.AccountService
import me.mitul.todo.model.service.trace
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(private val auth: FirebaseAuth) : AccountService {
    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(element = auth.currentUser?.let {
                    User(id = it.uid, isAnonymous = it.isAnonymous)
                } ?: User())
            }
            auth.addAuthStateListener(/* listener = */ listener)
            awaitClose {
                auth.removeAuthStateListener(/* listener = */ listener)
            }
        }

    override suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(
            /* email = */
            email,
            /* password = */
            password,
        ).await()
    }

    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(/* email = */ email)
            .await()
    }

    override suspend fun createAnonymousAccount() {
        auth.signInAnonymously()
            .await()
    }

    override suspend fun linkAccount(email: String, password: String): Unit =
        trace(LINK_ACCOUNT_TRACE) {
            val credential = EmailAuthProvider.getCredential(
                /* email = */
                email,
                /* password = */
                password,
            )

            auth.currentUser!!.linkWithCredential(/* credential = */ credential)
                .await()
        }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun signOut() {
        if (auth.currentUser!!.isAnonymous) {
            auth.currentUser!!.delete()
        }
        auth.signOut()

        // Sign the user back in anonymously.
        createAnonymousAccount()
    }

    companion object {
        private const val LINK_ACCOUNT_TRACE = "linkAccount"
    }
}
