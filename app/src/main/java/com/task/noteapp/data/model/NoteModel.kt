package com.task.noteapp.data.model

import com.task.noteapp.base.ListAdapterItem
import com.task.noteapp.testutil.functional.now
import java.io.Serializable
import java.util.*

data class NoteModel(
    override val id: Int = 0,
    var title: String?,
    var description: String?,
    val createdDate: String = Date().now(),
    var image: String? = null,
    val isUpdated: Boolean = false
) : Serializable, ListAdapterItem