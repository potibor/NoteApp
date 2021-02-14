package com.task.noteapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.domain.FetchNotesUseCase
import com.task.noteapp.util.Event
import com.task.noteapp.util.Failure
import com.task.noteapp.util.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNotesUseCase: FetchNotesUseCase
) : ViewModel() {

    val navigation = MutableLiveData<Event<Boolean>>()

    private fun handleError(failure: Failure) {
        Log.d("failllll", failure.toString())
    }

    private fun submitList(noteList: List<NoteModel>) {
        Log.d("successsssss", noteList.toString())
    }

    fun onAddButtonClick() {
        navigation.value = Event(true)
    }

    fun fetchNotes() {
        fetchNotesUseCase.invoke(viewModelScope, UseCase.None) {
            it.either(::handleError, ::submitList)
        }
    }
}