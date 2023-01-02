package me.mitul.todo.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.mitul.todo.common.snackbar.SnackBarManager
import me.mitul.todo.common.snackbar.SnackBarMessage.Companion.toSnackBarMessage
import me.mitul.todo.model.service.LogService

open class TodoViewModel(private val logService: LogService) : ViewModel() {
    fun launchCatching(
        snackBar: Boolean = true,
        block: suspend CoroutineScope.() -> Unit,
    ) = viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
        if (snackBar) SnackBarManager.showMessage(throwable.toSnackBarMessage())
        logService.logNonFatalCrash(throwable)
    }, block = block)
}
