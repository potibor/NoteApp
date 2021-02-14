package com.task.noteapp.ui.notehome

import com.task.noteapp.data.model.NoteModel

interface HomeClickListener {

    fun noteItemClicked(model: NoteModel)
}