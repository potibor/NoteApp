package com.task.noteapp.ui.home

import com.task.noteapp.data.model.NoteModel

interface HomeClickListener {

    fun noteItemClicked(model: NoteModel)
}