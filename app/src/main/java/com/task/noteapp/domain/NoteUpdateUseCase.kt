package com.task.noteapp.domain

import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.data.repository.NotesRepository
import com.task.noteapp.util.UseCase
import javax.inject.Inject

class NoteUpdateUseCase @Inject constructor(
    private val repository: NotesRepository
) : UseCase<Unit, NoteUpdateUseCase.Params>() {

    override suspend fun buildUseCase(params: Params) = with(params) {
        repository.update(noteModel)
    }

    data class Params(
        val noteModel: NoteModel
    )
}