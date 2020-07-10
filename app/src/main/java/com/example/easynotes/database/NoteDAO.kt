package com.example.easynotes.database

import com.example.easynotes.model.Note
import androidx.room.*

@Dao
interface NoteDAO{
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE id IN (:notesIds)")
    fun loadAllByIds(notesIds: IntArray): List<Note>

    @Query("SELECT * FROM note WHERE id=(:noteId)")
    fun getById(noteId: Long): Note

    @Insert
    fun insertAll(vararg notes: Note)

    @Update
    fun updateNotes(notes:Note)

    @Delete
    fun delete(notes: Note)
}