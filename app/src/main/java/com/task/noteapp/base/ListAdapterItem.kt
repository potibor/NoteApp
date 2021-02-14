package com.task.noteapp.base

interface ListAdapterItem {
    val id: Int

    override fun equals(other: Any?): Boolean
}