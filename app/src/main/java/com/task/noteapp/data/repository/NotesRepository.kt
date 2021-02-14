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
        var response = localDataSource.getAll()
        return response.filter {
            !it.title.isNullOrEmpty()
        }
    }

    suspend fun addNote(title: String?, description: String?, imageUrl: String?) {
        return localDataSource.add(
            NoteModel(
                title = title,
                description = description,
                image = imageUrl
            )
        )
    }

    suspend fun getNote(id: Int): NoteModel {
        return localDataSource.get(id)
    }


}