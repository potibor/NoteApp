package com.task.noteapp.ui.home

import com.task.noteapp.R
import com.task.noteapp.base.BaseListAdapter
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.databinding.ItemNoteListBinding

class HomeAdapter(
    private val listener: HomeClickListener
) : BaseListAdapter<ItemNoteListBinding, NoteModel>() {

    override val layoutRes: Int = R.layout.item_note_list

    override fun bind(
        binding: ItemNoteListBinding,
        item: NoteModel
    ) {
        binding.model = item
        binding.listener = listener
    }
}