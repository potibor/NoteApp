package com.task.noteapp.domain

import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.data.repository.NotesRepository
import com.task.noteapp.testutil.UseCase
import javax.inject.Inject


class NoteDeleteUseCase @Inject constructor(
    private val repository: NotesRepository
) : UseCase<Unit, NoteDeleteUseCase.Params>() {

    override suspend fun buildUseCase(params: Params) = with(params) {
        repository.deleteNote(noteModel)
    }

    data class Params(
        val noteModel: NoteModel
    )
}