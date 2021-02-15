package com.task.noteapp.ui.notedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.domain.NoteDeleteUseCase
import com.task.noteapp.domain.NoteDetailUseCase
import com.task.noteapp.domain.NoteUpdateUseCase
import com.task.noteapp.testutil.Event
import com.task.noteapp.testutil.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteDetailUseCase: NoteDetailUseCase,
    private val noteDeleteUseCase: NoteDeleteUseCase,
    private val noteUpdateUseCase: NoteUpdateUseCase
) : ViewModel() {

    val noteModel = MutableLiveData<NoteModel>()
    val navigateBackLiveData = MutableLiveData<Event<Boolean>>()
    val failureLiveData = MutableLiveData<Failure>()

    fun getNoteDetail(noteId: Int) = viewModelScope.launch {
        noteDetailUseCase.run(NoteDetailUseCase.Params(id = noteId))
            .either(::handleFailure, ::postModel)
    }

    fun editNote() = viewModelScope.launch {
        noteModel.value?.let { NoteUpdateUseCase.Params(noteModel = it) }?.let { params ->
            noteUpdateUseCase.run(params).either(::handleFailure, ::navigateBack)
        }
    }

    fun deleteNote() = viewModelScope.launch {
        noteModel.value?.let { NoteDeleteUseCase.Params(noteModel = it) }?.let { params ->
            noteDeleteUseCase.run(params).either(::handleFailure, ::navigateBack)
        }
    }

    private fun navigateBack() {
        navigateBackLiveData.value = Event(true)
    }

    private fun handleFailure(failure: Failure) {
        failureLiveData.value = failure
    }

    private fun postModel(response: NoteModel) {
        noteModel.value = response
    }
}