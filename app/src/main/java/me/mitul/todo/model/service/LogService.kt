package me.mitul.todo.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}
