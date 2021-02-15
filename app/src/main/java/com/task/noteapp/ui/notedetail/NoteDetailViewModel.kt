package com.task.noteapp.ui.notedetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.domain.NoteDeleteUseCase
import com.task.noteapp.domain.NoteDetailUseCase
import com.task.noteapp.domain.NoteUpdateUseCase
import com.task.noteapp.util.Event
import com.task.noteapp.util.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteDetailUseCase: NoteDetailUseCase,
    private val noteDeleteUseCase: NoteDeleteUseCase,
    private val noteUpdateUseCase: NoteUpdateUseCase,
) : ViewModel() {

    val noteModel = MutableLiveData<NoteModel>()
    val navigateBackLiveData = MutableLiveData<Event<Boolean>>()

    fun getNoteDetail(noteId: Int) {
        noteDetailUseCase.invoke(viewModelScope, NoteDetailUseCase.Params(id = noteId)) {
            it.either(::handleFailure, ::postModel)
        }
    }

    fun editNote() {
        noteModel.value?.let { NoteUpdateUseCase.Params(noteModel = it) }?.let { params ->
            noteUpdateUseCase.invoke(viewModelScope, params) {
                it.either(::handleFailure, ::navigateBack)
            }
        }
    }

    fun deleteNote() {
        noteModel.value?.let { NoteDeleteUseCase.Params(noteModel = it) }?.let { params ->
            noteDeleteUseCase.invoke(viewModelScope, params) {
                it.either(::handleFailure, ::navigateBack)
            }
        }
    }

    private fun navigateBack() {
        navigateBackLiveData.value = Event(true)
    }

    private fun handleFailure(failure: Failure) {
        Log.d("TAG", failure.toString())
    }

    private fun postModel(response: NoteModel) {
        noteModel.value = response
    }
}