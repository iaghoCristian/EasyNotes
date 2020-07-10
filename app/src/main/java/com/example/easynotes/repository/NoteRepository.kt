package com.example.easynotes.repository

import com.example.easynotes.model.Note
import android.content.Context
import android.provider.ContactsContract
import androidx.room.Room
import com.example.easynotes.database.Database
import com.example.easynotes.database.databaseApp
import com.example.easynotes.model.Frequency
import com.example.easynotes.tasks.DeleteNoteTask
import com.example.easynotes.tasks.InsertNoteTask
import com.example.easynotes.tasks.UpdateNoteTask
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

public class NoteRepository(var context: Context): Serializable {

    val db : databaseApp

    init{
        db = Database.getDatabase(context) as databaseApp
    }

    fun addNote(note: Note){
        InsertNoteTask(db,note).execute()
    }

    fun deleteNote(note: Note){
        DeleteNoteTask(db,note).execute()
    }


    fun getById(noteID: Long):Note?{
        return db.noteDao().getById(noteID)
    }

    fun getByIndex(index: Int): Note?{
        var notes: List<Note> = db.noteDao().getAll()
        return notes.get(index)
    }

    fun returnSize(): Int{
        var notes: List<Note> = db.noteDao().getAll()
        return notes.size
    }

    fun noteUpdate(note:Note){
        UpdateNoteTask(db,note).execute()
    }
}