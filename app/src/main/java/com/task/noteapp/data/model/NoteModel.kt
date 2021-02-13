package com.task.noteapp.data.model

import java.io.Serializable

data class NoteModel(
    val id: Int = 0,
    var title: String,
    var description: String,
    val createdDate: String = "",
    var image: String? = null,
    val isUpdated: Boolean = false
) : Serializable