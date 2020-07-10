package com.example.easynotes.tasks

import com.example.easynotes.model.Note
import android.os.AsyncTask
import com.example.easynotes.database.databaseApp

class DeleteNoteTask(db:databaseApp, note:Note):AsyncTask<Void,Void,Unit>(){

    var db: databaseApp
    var note: Note

    init{
        this.db = db
        this.note = note
    }

    override fun doInBackground(vararg params: Void) {
        db.noteDao().delete(note)
    }
}