package com.task.noteapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.task.noteapp.data.model.NoteModel

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "image") val image: String? = null,
    @ColumnInfo(name = "createdDate") val createdDate: String,
    @ColumnInfo(name = "isUpdated") val isUpdated: Boolean = false
) {

    fun toNoteModel(): NoteModel = NoteModel(
        id, title, description, createdDate, image
    )

}