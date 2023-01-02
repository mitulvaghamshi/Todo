package me.mitul.todo.common.snackbar

import android.content.res.Resources
import androidx.annotation.StringRes
import me.mitul.todo.R.string as AppText

sealed class SnackBarMessage {
    class StringSnackBar(val message: String) : SnackBarMessage()
    class ResourceSnackBar(@StringRes val message: Int) : SnackBarMessage()

    companion object {
        fun SnackBarMessage.toMessage(resources: Resources): String = when (this) {
            is StringSnackBar -> this.message
            is ResourceSnackBar -> resources.getString(/* id = */ this.message)
        }

        fun Throwable.toSnackBarMessage(): SnackBarMessage {
            val message = this.message.orEmpty()

            return if (message.isNotBlank()) {
                StringSnackBar(message = message)
            } else {
                ResourceSnackBar(message = AppText.generic_error)
            }
        }
    }
}
