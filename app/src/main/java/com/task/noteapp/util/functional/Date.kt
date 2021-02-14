package com.task.noteapp.util.functional

import java.text.SimpleDateFormat
import java.util.*

fun Date.now(): String {
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault())
    return sdf.format(this)
}