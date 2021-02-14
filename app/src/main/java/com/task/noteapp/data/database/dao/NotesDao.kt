package com.task.noteapp.data.database.dao

import androidx.room.*
import com.task.noteapp.data.database.entity.NoteEntity

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: NoteEntity)

    @Query("UPDATE note SET title=:title, description=:description, image=:image, isUpdated=:isUpdated WHERE id=:id")
    suspend fun updateNote(
        id: Int,
        title: String?,
        description: String?,
        image: String?,
        isUpdated: Boolean
    )

    @Delete
    suspend fun removeNote(note: NoteEntity)

    @Query("SELECT * FROM note")
    suspend fun getNotes(): List<NoteEntity>

    @Query("SELECT * FROM note WHERE id=:id")
    suspend fun getNote(id: Int): NoteEntity

}