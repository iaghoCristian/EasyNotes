package com.example.easynotes.database

import android.content.Context
import androidx.room.Room

object Database{

    @get:Synchronized
    private var db: databaseApp? = null
    private set
    private get

    fun getDatabase(context: Context):databaseApp?{
        if(db ==null) db = Room.databaseBuilder(
            context,
            databaseApp::class.java,
            "notes-db"
        ).build()
        return db
    }
}