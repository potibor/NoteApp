package com.task.noteapp.testutil

import java.io.IOException

sealed class Failure : IOException() {
    object IgnorableError : Failure()
}