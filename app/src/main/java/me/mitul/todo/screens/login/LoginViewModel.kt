package me.mitul.todo.screens.login

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import me.mitul.todo.common.LOGIN_SCREEN
import me.mitul.todo.common.SETTINGS_SCREEN
import me.mitul.todo.common.extension.isValidEmail
import me.mitul.todo.common.snackbar.SnackBarManager
import me.mitul.todo.common.snackbar.SnackBarManager.showMessage
import me.mitul.todo.model.service.AccountService
import me.mitul.todo.model.service.LogService
import me.mitul.todo.screens.TodoViewModel
import javax.inject.Inject
import me.mitul.todo.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService,
) : TodoViewModel(logService) {
    var uiState = mutableStateOf(value = LoginUiState()); private set

    private val email; get() = uiState.value.email
    private val password; get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(popUpAndPush: (String, String) -> Unit) = launchCatching {
        accountService.authenticate(email = email, password = password)
        popUpAndPush(SETTINGS_SCREEN, LOGIN_SCREEN)
    }

    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            showMessage(message = AppText.email_error)
            return
        }
        launchCatching {
            accountService.sendRecoveryEmail(email = email)
            showMessage(message = AppText.recovery_email_sent)
        }
    }

    fun isValid(): Boolean {
        if (!email.isValidEmail()) {
            showMessage(message = AppText.email_error)
            return false
        }
        if (password.isBlank()) {
            showMessage(message = AppText.empty_password_error)
            return false
        }
        return true
    }
}
