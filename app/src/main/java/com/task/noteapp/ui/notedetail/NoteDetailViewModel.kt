package com.task.noteapp.ui.notedetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.domain.NoteDetailUseCase
import com.task.noteapp.util.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteDetailUseCase: NoteDetailUseCase
) : ViewModel() {

    private val noteModel = MutableLiveData<NoteModel>()

    fun getNoteDetail(noteId: Int) {
        noteDetailUseCase.invoke(viewModelScope, NoteDetailUseCase.Params(id = noteId)) {
            it.either(::handleFailure, ::postModel)
        }
    }

    private fun handleFailure(failure: Failure) {
        Log.d("TAG", failure.toString())
    }

    private fun postModel(response: NoteModel) {
        noteModel.value = response
    }
}