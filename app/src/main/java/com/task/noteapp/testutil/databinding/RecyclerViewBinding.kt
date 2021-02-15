package com.task.noteapp.testutil.databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.base.BaseListAdapter
import com.task.noteapp.base.ListAdapterItem

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(view: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = view.adapter as BaseListAdapter<ViewDataBinding, ListAdapterItem>?
    adapter?.submitList(list)
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: BaseListAdapter<ViewDataBinding, ListAdapterItem>?) {
    adapter?.let {
        view.adapter = it
    }
}