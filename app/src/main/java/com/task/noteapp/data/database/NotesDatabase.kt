package com.task.noteapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.noteapp.data.database.entity.NoteEntity
import com.task.noteapp.data.database.dao.NotesDao


@Database(
    entities = [NoteEntity::class],
    version = 3,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

}