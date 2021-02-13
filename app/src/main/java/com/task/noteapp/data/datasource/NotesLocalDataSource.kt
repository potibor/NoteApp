package com.task.noteapp.data.datasource

import com.task.noteapp.data.database.dao.NotesDao
import com.task.noteapp.data.database.entity.NoteEntity
import com.task.noteapp.data.model.NoteModel
import javax.inject.Inject

class NotesLocalDataSource @Inject constructor(
    private val notesDao: NotesDao
) {

    suspend fun add(note: NoteModel) {
        notesDao.addNote(
            NoteEntity(
                title = note.title,
                description = note.description,
                image = note.image,
                createdDate = note.createdDate
            )
        )
    }

    suspend fun update(note: NoteModel) {
        notesDao.updateNote(
            id = note.id,
            title = note.title,
            description = note.description,
            image = note.image,
            isUpdated = true
        )
    }

    suspend fun remove(note: NoteModel) {
        notesDao.removeNote(
            NoteEntity(
                id = note.id,
                title = note.title,
                description = note.description,
                image = note.image,
                createdDate = note.createdDate
            )
        )
    }

    suspend fun getAll(): List<NoteModel> =
        notesDao.getNotes().map {
            NoteEntity(
                id = it.id,
                title = it.title,
                description = it.description,
                createdDate = it.createdDate,
                image = it.image,
                isUpdated = it.isUpdated
            ).toNoteModel()
        }

    suspend fun get(id: Int): NoteModel =
        notesDao.getNote(id).toNoteModel()

}