package me.mitul.todo.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import me.mitul.todo.common.SETTINGS_SCREEN
import me.mitul.todo.common.SIGN_UP_SCREEN
import me.mitul.todo.common.extension.isValidEmail
import me.mitul.todo.common.extension.isValidPassword
import me.mitul.todo.common.extension.passwordMatches
import me.mitul.todo.common.snackbar.SnackBarManager.showMessage
import me.mitul.todo.model.service.AccountService
import me.mitul.todo.model.service.LogService
import me.mitul.todo.screens.TodoViewModel
import javax.inject.Inject
import me.mitul.todo.R.string as AppText

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService,
) : TodoViewModel(logService) {
    var uiState = mutableStateOf(value = SignUpUiState()); private set

    private val email; get() = uiState.value.email
    private val password; get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(popUpAndPush: (String, String) -> Unit) = launchCatching {
        accountService.linkAccount(email = email, password = password)
        popUpAndPush(SETTINGS_SCREEN, SIGN_UP_SCREEN)
    }

    fun isValid(): Boolean {
        if (!email.isValidEmail()) {
            showMessage(message = AppText.email_error)
            return false
        }
        if (!password.isValidPassword()) {
            showMessage(message = AppText.password_error)
            return false
        }
        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            showMessage(message = AppText.password_match_error)
            return false
        }
        return true
    }
}
