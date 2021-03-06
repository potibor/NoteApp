package com.task.noteapp.ui.notehome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.domain.FetchNotesUseCase
import com.task.noteapp.testutil.Event
import com.task.noteapp.testutil.Failure
import com.task.noteapp.testutil.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNotesUseCase: FetchNotesUseCase
) : ViewModel() {

    val navigation = MutableLiveData<Event<Boolean>>()

    val noteListLiveData = MutableLiveData<Event<List<NoteModel>>>()
    val errorFailureLiveData = MutableLiveData<Event<Boolean>>()

    fun fetchNotes() = viewModelScope.launch {
        fetchNotesUseCase.run(UseCase.None).either(::handleError, ::submitList)
    }

    private fun handleError(failure: Failure) {
        errorFailureLiveData.value = Event(true)
    }

    private fun submitList(noteList: List<NoteModel>) {
        noteListLiveData.value = Event(noteList)
    }

    fun onAddButtonClick() {
        navigation.value = Event(true)
    }
}