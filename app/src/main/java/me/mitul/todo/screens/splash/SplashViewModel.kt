package me.mitul.todo.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import me.mitul.todo.common.SPLASH_SCREEN
import me.mitul.todo.common.TASKS_SCREEN
import me.mitul.todo.model.service.AccountService
import me.mitul.todo.model.service.ConfigurationService
import me.mitul.todo.model.service.LogService
import me.mitul.todo.screens.TodoViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    configurationService: ConfigurationService,
    private val accountService: AccountService,
    logService: LogService,
) : TodoViewModel(logService) {
    val showError = mutableStateOf(false)

    init {
        launchCatching {
            configurationService.fetchConfiguration()
        }
    }

    fun onAppStart(popUpAndPush: (String, String) -> Unit) {
        showError.value = false
        if (accountService.hasUser) {
            popUpAndPush(TASKS_SCREEN, SPLASH_SCREEN)
        } else {
            createAnonymousAccount(popUpAndPush = popUpAndPush)
        }
    }

    private fun createAnonymousAccount(popUpAndPush: (String, String) -> Unit) {
        launchCatching(snackBar = false) {
            try {
                accountService.createAnonymousAccount()
            } catch (ex: FirebaseAuthException) {
                showError.value = true
                throw ex
            }
            popUpAndPush(TASKS_SCREEN, SPLASH_SCREEN)
        }
    }
}
