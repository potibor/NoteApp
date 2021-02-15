package com.task.noteapp.domain

import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.data.repository.NotesRepository
import com.task.noteapp.testutil.UseCase
import javax.inject.Inject

class NoteDetailUseCase @Inject constructor(
    private val repository: NotesRepository
) : UseCase<NoteModel, NoteDetailUseCase.Params>() {

    override suspend fun buildUseCase(params: Params) = with(params) {
        repository.getNote(id)
    }

    data class Params(
        val id: Int
    )
}