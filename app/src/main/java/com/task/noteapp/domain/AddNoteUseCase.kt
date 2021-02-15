package com.task.noteapp.domain

import com.task.noteapp.data.repository.NotesRepository
import com.task.noteapp.testutil.UseCase
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) : UseCase<Unit, AddNoteUseCase.Params>() {

    override suspend fun buildUseCase(params: Params) = with(params) {
        repository.addNote(title, description, imageUrl)
    }

    data class Params(
        val title: String?,
        val description: String?,
        val imageUrl: String?,
    )
}