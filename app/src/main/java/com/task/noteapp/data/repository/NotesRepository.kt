package com.task.noteapp.data.repository

import com.task.noteapp.data.datasource.NotesLocalDataSource
import com.task.noteapp.data.model.NoteModel
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val localDataSource: NotesLocalDataSource
) {
    /*
    * we can map response, if necessary
    */
    suspend fun fetchNotes(): List<NoteModel> {
        return localDataSource.getAll()
    }


}