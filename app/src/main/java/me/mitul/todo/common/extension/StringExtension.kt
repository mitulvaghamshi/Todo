package me.mitul.todo.common.extension

import android.util.Patterns
import java.util.regex.Pattern

private const val MIN_PASS_LENGTH = 6
private const val PASS_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

fun String.isValidEmail(): Boolean =
    this.isNotBlank() && Patterns.EMAIL_ADDRESS
        .matcher(/* input = */ this)
        .matches()

fun String.isValidPassword(): Boolean =
    this.isNotBlank() && this.length >= MIN_PASS_LENGTH && Pattern
        .compile(PASS_PATTERN)
        .matcher(this)
        .matches()

fun String.passwordMatches(repeated: String): Boolean = this == repeated

fun String.idFromParameter(): String = this.substring(1, this.length - 1)
