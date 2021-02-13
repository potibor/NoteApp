package com.task.noteapp.domain

import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.data.repository.NotesRepository
import com.task.noteapp.util.UseCase
import javax.inject.Inject


class FetchNotesUseCase @Inject constructor(
    private val repository: NotesRepository
) : UseCase<List<NoteModel>, UseCase.None>() {

    override suspend fun buildUseCase(params: None) = repository.fetchNotes()
}