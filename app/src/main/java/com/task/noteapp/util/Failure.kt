package com.task.noteapp.util

import java.io.IOException
import java.lang.Exception

sealed class Failure : IOException() {
    class UnknownError(val exception: Exception) : Failure()
}