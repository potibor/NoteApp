package com.task.noteapp.ui.addnote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.domain.AddNoteUseCase
import com.task.noteapp.util.Event
import com.task.noteapp.util.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val addNotesUseCase: AddNoteUseCase
) : ViewModel() {

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()
    val errorFailureLiveData = MutableLiveData<Event<Boolean>>()
    val successLiveData = MutableLiveData<Event<Boolean>>()

    fun addNote() {
        if (title.value.isNullOrEmpty() && description.value.isNullOrEmpty()) {
            errorFailureLiveData.value = Event(true)
        } else {
            addNotesUseCase.invoke(
                viewModelScope,
                AddNoteUseCase.Params(
                    title = title.value,
                    description = description.value,
                    imageUrl = imageUrl.value
                )
            ) {
                it.either(::handleFailure, ::navigateBack)
            }
        }
    }

    private fun handleFailure(failure: Failure) {
        errorFailureLiveData.value = Event(true)
    }

    private fun navigateBack() {
        successLiveData.value = Event(true)
    }
}